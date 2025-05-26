INSERT INTO company_id
VALUES (UNHEX('0196f7a610b67123a2dc32c3861ea55e'));

-- 2. 그 ID를 참조해서 실제 company 테이블에 insert
INSERT INTO company (id,
                     name,
                     contact_phone_number,
                     business_number,
                     address,
                     contact_email,
                     detail,
                     logo_image_path,
                     type,
                     deleted,
                     created_at,
                     modified_at)
VALUES (UNHEX('0196f7a610b67123a2dc32c3861ea55e'),
        '테스트회사',
        '010-1234-5678',
        '123-45-67890',
        '서울시 테스트구',
        'test@company.com',
        '삭제 테스트용',
        'path/logo.png',
        'DEV',
        false,
        NOW(),
        NOW());