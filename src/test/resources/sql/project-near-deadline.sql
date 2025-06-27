-- 회사 (개발사, 고객사)
INSERT INTO company (
    id, name, contact_email, business_number, address, contact_phone_number,
    created_at, modified_at, type, file_name, detail, deleted
)
VALUES
    (
        UNHEX(REPLACE('019759dd-378a-7590-9bd4-b204a064a120', '-', '')),
        '개발사', 'dev@example.com', '123-45-67890', '서울시 강남구', '02-1234-5678',
        NOW(), NOW(), 'DEV', 'dev.png', '개발사입니다.', 0
    ),
    (
        UNHEX(REPLACE('019759de-4cdf-70e6-a0c9-3188cac11476', '-', '')),
        '고객사', 'client@example.com', '987-65-43210', '서울시 서초구', '02-8765-4321',
        NOW(), NOW(), 'CLIENT', 'client.png', '고객사입니다.', 0
    );

-- 프로젝트
INSERT INTO project (
    deleted, created_at, end_at, modified_at, start_at,
    id, name, step, detail,project_amount
)
VALUES
    (
        0, NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), NOW(), NOW(),
        UNHEX(REPLACE('01aaffdd-1111-2222-3333-444455556666', '-', '')),
        '마감 임박 프로젝트', 'IN_PROGRESS', '마감 임박 테스트용 프로젝트입니다.',100
    );

-- 프로젝트-회사 할당 정보
INSERT INTO project_assign (
    created_at, client_company_id, dev_company_id,
    id, project_id
)
VALUES
    (
        NOW(),
        UNHEX(REPLACE('019759de-4cdf-70e6-a0c9-3188cac11476', '-', '')),
        UNHEX(REPLACE('019759dd-378a-7590-9bd4-b204a064a120', '-', '')),
        UNHEX(REPLACE('01bbccdd-1111-2222-3333-444455556677', '-', '')),
        UNHEX(REPLACE('01aaffdd-1111-2222-3333-444455556666', '-', ''))
    );
