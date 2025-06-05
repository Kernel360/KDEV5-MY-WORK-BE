INSERT INTO review (
    deleted, created_at, modified_at, id, member_id, parent_id, post_id, company_name, comment, author_name
) VALUES (
    b'0',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    UNHEX(REPLACE('0197385a-eda5-7e17-a3ce-4252908f8d1f', '-', '')),
    UNHEX(REPLACE('0197385b-1adf-79fc-8a98-b25b57cd4d92', '-', '')), -- TODO 인증 로직 추가 후 변경하기
    NULL,
    UNHEX(REPLACE('9f8e7d6c-5b4a-3928-1706-958493827361', '-', '')),
    'OpenAI',
    '좋은 리뷰입니다. 감사합니다!',
    '홍길동'
    );
