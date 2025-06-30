INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UUID_TO_BIN('33333333-3333-3333-3333-333333333333'),
             UUID_TO_BIN('6939d8be-1bf2-4f01-9189-12864e38d913'),
             '홍길동', '기획팀', '팀장',
             'ANONYMOUS', '010-1234-5678', 'reviewer@example.com', 'password123', 0,
             NOW(), NOW(), '1990-01-01 00:00:00'
         );

INSERT INTO project (
    id, name, start_at, end_at, step,
    created_at, modified_at, detail,
    deleted, project_amount
) VALUES (
             UUID_TO_BIN('22222222-2222-2222-2222-222222222222'),
             '리뷰 테스트용 프로젝트',
             NOW(), NOW(), 'STEP1',
             NOW(), NOW(), '리뷰 테스트용 프로젝트입니다.',
             0, 0
         );


INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (
           UUID_TO_BIN('11111111-1111-1111-1111-111111111111'),
           UUID_TO_BIN('22222222-2222-2222-2222-222222222222'),
           '기획 단계',
           1,
           NOW()
       );

INSERT INTO post (
    id, title, content, project_step_id, approval, author_id, author_name,
    company_name, created_at, modified_at, deleted
) VALUES (
             UUID_TO_BIN('01972f9b-232a-7dbe-aad2-3bffc0b78ced'),
             '리뷰용 게시글',
             '내용입니다.',
             UUID_TO_BIN('11111111-1111-1111-1111-111111111111'), -- 프로젝트 스텝 UUID (존재해야 함)
             'APPROVED',
             UUID_TO_BIN('33333333-3333-3333-3333-333333333333'), -- authorId (위에서 만든 홍길동)
             '홍길동',
             'AUser',
             NOW(), NOW(), 0
         );

INSERT INTO review (id, member_id, parent_id, post_id,
                    company_name, comment, author_name, deleted,
                    created_at, modified_at)
VALUES (
           UUID_TO_BIN('019748f2-244f-702d-aa8c-a7b27d255c2e'),
           UUID_TO_BIN('33333333-3333-3333-3333-333333333333'),
           NULL,
           UUID_TO_BIN('01972f9b-232a-7dbe-aad2-3bffc0b78ced'),
           'Company01',
           '기존 리뷰입니다.',
           '홍길동',
           b'0',
           '2025-06-01 12:00:00',
           '2025-06-01 12:00:00'
       );
