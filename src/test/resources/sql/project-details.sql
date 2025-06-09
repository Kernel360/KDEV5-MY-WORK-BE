INSERT INTO company (id, name, detail, business_number, address, type,
                     contact_phone_number, contact_email, logo_image_path,
                     created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '개발사_이름',
        '개발사 세부 내용입니다.',
        '123-45-67890',
        '서울시 강남구 테헤란로 123',
        'DEV',
        '010-1234-5678',
        'admin@dev.com',
        '/images/logo.png',
        NOW(), NOW(), FALSE);

INSERT INTO company (id, name, detail, business_number, address, type,
                     contact_phone_number, contact_email, logo_image_path,
                     created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')),
        '고객사 이름',
        '고객사 세부 내용입니다',
        '123-95-29880',
        '서울시 강남구 테헤란로 123',
        'CLIENT',
        '010-1234-5678',
        'admin@client.com',
        '/images/logo.png',
        NOW(), NOW(), FALSE);

INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES (UNHEX(REPLACE('01973a42-0995-74aa-9298-a25cb8dae6ef', '-', '')),
        '테스트 프로젝트',
        NOW(),
        NOW(),
        'IN_PROGRESS',
        NOW(),
        NOW(),
        '테스트용 프로젝트입니다',
        0);

insert into project_assign (created_at, client_company_id, dev_company_id, id, project_id)
values (NOW(),
        UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')),
        UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        UNHEX(REPLACE('0197538e-582b-736a-9e19-c560f219b0b6', '-', '')),
        UNHEX(REPLACE('01973a42-0995-74aa-9298-a25cb8dae6ef', '-', '')));
