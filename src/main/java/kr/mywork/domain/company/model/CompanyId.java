package kr.mywork.domain.company.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyId {

	@Id
	@Getter
	private UUID id;

	public CompanyId(final UUID id) {
		this.id = id;
	}
}
