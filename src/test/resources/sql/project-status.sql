INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES (UNHEX(REPLACE('01973a42-0995-74aa-9298-a25cb8dae6ef', '-', '')),
        '테스트 프로젝트',
        NOW(),
        NOW(),
        'IN_PROGRESS',
        NOW(),
        NOW(),
        '테스트용 프로젝트입니다',
        0,
        100);
