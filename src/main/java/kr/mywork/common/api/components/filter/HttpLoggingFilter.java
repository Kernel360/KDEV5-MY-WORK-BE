package kr.mywork.common.api.components.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.components.request.HttpRequestBodyCachedWrapper;
import kr.mywork.common.api.components.response.HttpResponseBodyCachedWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpLoggingFilter implements Filter {

	private static final String LARGE_VALUE = "[large value]";
	private static final String CONTENT_EMPTY = "[empty]";

	private static final int RESPONSE_BODY_MAX_BYTE_LENGTH = 1024;
	private static final int HEADER_MAX_SIZE = 300;

	private static final String HEADER_DELIMITER = "; ";
	private static final String HEADER_COLON = ": ";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpRequestBodyCachedWrapper requestWrapper =
			new HttpRequestBodyCachedWrapper((HttpServletRequest)request);
		HttpResponseBodyCachedWrapper responseWrapper =
			new HttpResponseBodyCachedWrapper((HttpServletResponse)response);

		logRequest(requestWrapper);

		chain.doFilter(requestWrapper, responseWrapper);

		logResponse(responseWrapper);
		writeResponseBody(responseWrapper, response);
	}

	private void logRequest(HttpRequestBodyCachedWrapper request) throws IOException {
		String url = request.getRequestURI();
		String method = request.getMethod();
		String headers = getRequestHeadersAsString(request);
		String body = getRequestBodyAsString(request);

		log.info("HTTP Request - URL: {}, Method: {}, Headers: {}, Body: {}", url, method, headers, body);
	}

	private String getRequestHeadersAsString(HttpServletRequest request) {
		final StringBuilder headers = new StringBuilder();
		final Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);

			if (headerValue.length() >= HEADER_MAX_SIZE) {
				headerValue = LARGE_VALUE;
			}

			headers.append(headerName).append(HEADER_COLON).append(headerValue).append(HEADER_DELIMITER);
		}
		return headers.toString().trim();
	}

	private String getRequestBodyAsString(final HttpRequestBodyCachedWrapper request) throws IOException {
		return new String(request.getCachedBody(), StandardCharsets.UTF_8);
	}

	private void logResponse(final HttpResponseBodyCachedWrapper response) throws IOException {
		final int status = response.getStatus();
		final Map<String, String> headersMap = getResponseHeaderMap(response);
		final String headers = getResponseHeadersAsString(headersMap);
		final String body = getResponseBodyAsString(response, headersMap);

		log.info("HTTP Response - Status: {}, Headers: {}, Body: {}", status, headers, body);
	}

	private Map<String, String> getResponseHeaderMap(final HttpResponseBodyCachedWrapper response) {
		Map<String, String> headersMap = new LinkedHashMap<>();
		final Collection<String> headerNames = response.getHeaderNames();

		for (String headerName : headerNames) {
			String headerValue = response.getHeader(headerName);

			// access token 값 로깅 방지용
			if (headerValue.length() >= HEADER_MAX_SIZE) {
				headerValue = LARGE_VALUE;
			}

			headersMap.put(headerName, headerValue);
		}

		return headersMap;

	}

	private String getResponseBodyAsString(
		final HttpResponseBodyCachedWrapper response,
		final Map<String, String> headersMap
	) throws IOException {
		if (!headersMap.containsKey(HttpHeaders.CONTENT_TYPE)) {
			return CONTENT_EMPTY;
		}

		if (!headersMap.get(HttpHeaders.CONTENT_TYPE).equals(MediaType.APPLICATION_JSON_VALUE)) {
			return CONTENT_EMPTY;
		}

		return (response.getCachedBody().length <= RESPONSE_BODY_MAX_BYTE_LENGTH) ?
			new String(response.getCachedBody(), StandardCharsets.UTF_8) : LARGE_VALUE;
	}

	private String getResponseHeadersAsString(Map<String, String> responseHeadersMap) {
		final StringBuilder headerBuilder = new StringBuilder();

		for (Map.Entry<String, String> headerEntry : responseHeadersMap.entrySet()) {
			headerBuilder.append(headerEntry.getKey()).append(HEADER_COLON);

			if (headerEntry.getValue().length() <= HEADER_MAX_SIZE) {
				headerBuilder.append(headerEntry.getValue()).append(HEADER_DELIMITER);
			} else {
				headerBuilder.append(LARGE_VALUE);
			}
		}

		return headerBuilder.toString().trim();
	}

	// 우회한 response body byte 코드 주입해야 하므로 제거 금지!!
	private void writeResponseBody(HttpResponseBodyCachedWrapper cachedResponse, ServletResponse response)
		throws IOException {
		final byte[] responseBody = cachedResponse.getCachedBody();
		response.getOutputStream().write(responseBody);
	}
}
