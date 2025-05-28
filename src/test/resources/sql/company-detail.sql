-- 회사 삽입
INSERT INTO company (id, name, detail, business_number, address, type,
                     contact_phone_number, contact_email, logo_image_path,
                     created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '마이워크',
        '업무 관리 솔루션 회사입니다.',
        '123-45-67890',
        '서울시 강남구 테헤란로 123',
        'DEV',
        '010-1234-5678',
        'info@mywork.com',
        '/images/logo.png',
        NOW(), NOW(), FALSE);
