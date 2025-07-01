INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES (UUID_TO_BIN('01975a03-765d-7760-b82f-1bc8ba1b2ab6'),
        '고객사 개발 프로젝트',
        NOW(),
        NOW(),
        'IN_PROGRESS',
        NOW(),
        NOW(),
        '고객사의 웹페이지를 구성해주는 프로젝트입니다',
        0,
        0);
