-- 기획 단계 (order_num: 1)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '기획', 1, NOW());

-- 디자인 단계 (order_num: 2)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '디자인', 2, NOW());

-- 개발 단계 (order_num: 3)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '개발', 3, NOW());

INSERT INTO project_check_list (id, author_id, author_name, company_id, company_name, project_step_id, title, content, approval, created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')),
        '홍길동',
        UNHEX(REPLACE('019762b1-aaaa-bbbb-cccc-ddddeeeeffff', '-', '')),
        '카카오엔터프라이즈',
        UNHEX(REPLACE('0197a421-44be-76ad-8f1b-f91a74ac5d5f', '-', '')),
        '프로젝트 체크 리스트 타이틀',
        '프로젝트 체크 리스트 내용',
        'PENDING',
        CURRENT_TIMESTAMP(6),
        CURRENT_TIMESTAMP(6),
        0);
