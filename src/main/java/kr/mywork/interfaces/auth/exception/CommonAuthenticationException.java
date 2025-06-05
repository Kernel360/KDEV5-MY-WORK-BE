package kr.mywork.interfaces.auth.exception;

import kr.mywork.common.api.support.error.CommonErrorType;
import lombok.Getter;

@Getter
public class CommonAuthenticationException extends RuntimeException {

  private final CommonErrorType errorType;

  public CommonAuthenticationException(CommonErrorType errorType) {
    super(errorType.getMessage());
    this.errorType = errorType;
  }
}
