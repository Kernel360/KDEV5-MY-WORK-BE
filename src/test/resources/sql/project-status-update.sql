INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES (UUID_TO_BIN('01974f0b-5c7a-7fa2-9aba-1323490b77e9'),
        '고객사 개발 프로젝트',
        NOW(),
        NOW(),
        'IN_PROGRESS',
        NOW(),
        NOW(),
        '고객사의 웹페이지를 구성해주는 프로젝트입니다',
        0,
        0);
