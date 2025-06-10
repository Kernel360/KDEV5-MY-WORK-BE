package kr.mywork.interfaces.member.controller.dto.request;

public record ResetPasswordWebRequest(String email, String password, String newPassword){
}
