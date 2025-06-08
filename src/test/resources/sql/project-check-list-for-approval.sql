-- 회사 ID 먼저 생성
INSERT INTO company_id
VALUES (UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')));
INSERT INTO company_id
VALUES (UNHEX(REPLACE('01973f6c-4b84-70e3-be24-1daf26e5808a', '-', '')));

-- 회사 데이터 생성
INSERT INTO company (id,
                     name,
                     detail,
                     business_number,
                     address,
                     type,
                     contact_phone_number,
                     contact_email,
                     logo_image_path,
                     created_at,
                     modified_at,
                     deleted)
VALUES (UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')),
        '고객사 회사',
        '회사 상세 설명',
        '323-45-67890',
        '서울시 강남구 테헤란로 123',
        'CLIENT',
        '02-1234-5678',
        'client@company.com',
        '/image/url/1234a9a9-90b6-9898-a9dc-92c9861aa98c',
        NOW(),
        NOW(),
        FALSE),
       (UNHEX(REPLACE('01973f6c-4b84-70e3-be24-1daf26e5808a', '-', '')),
        '개발사 회사',
        '회사 상세 설명',
        '223-45-67890',
        '서울시 강남구 테헤란로 123',
        'DEV',
        '02-1234-5678',
        'dev@company.com',
        '/image/url/01973f6c-4b84-70e3-be24-1daf26e5808a',
        NOW(),
        NOW(),
        FALSE);

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

INSERT INTO project_check_list (
    id,
    title,
    dev_company_id,
    client_company_id,
    project_step_id,
    approval,
    created_at,
    deleted
) VALUES (
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
             '프로젝트 체크 리스트 타이틀',
             UNHEX(REPLACE('01973f6c-4b84-70e3-be24-1daf26e5808a', '-', '')),
             UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')),
             UNHEX(REPLACE('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01', '-', '')),
             '대기',
             NOW(),
             false
 );
