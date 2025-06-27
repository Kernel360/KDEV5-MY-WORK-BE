package kr.mywork.domain.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectAmountChartRole {
	CHART_WEEK("CHART_TYPE_WEEK"),
	CHART_MONTH("CHART_TYPE_MONTH");

	private final String chartType;

	public boolean isSameChartType(final String chartType) {
		return this.chartType.equals(chartType);

	}
}