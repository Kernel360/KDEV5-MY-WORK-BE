package kr.mywork.domain.member.errors;

public abstract class MemberException extends RuntimeException {
  private final MemberErrorType errorType;

  public MemberException(final MemberErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
