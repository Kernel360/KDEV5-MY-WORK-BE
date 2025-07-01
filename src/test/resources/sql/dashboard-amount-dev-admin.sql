-- 데브 어드민 회사 생성
-- 회사 삽입
INSERT INTO company (id, name, detail, business_number, address, type,contact_phone_number, contact_email, file_name,created_at, modified_at, deleted)
VALUES (
        UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')),
        '마이워크',
        '업무 관리 솔루션 회사입니다.',
        '123-45-67890',
        '서울시 강남구 테헤란로 123',
        'DEV',
        '010-1234-5678',
        'info@mywork.com',
        'logo.png',
        NOW(),
        NOW(),
        FALSE
        );


INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted, project_amount)
VALUES
(UNHEX(REPLACE('0197a204-c3b4-733b-81dd-9657a58599e6', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-11 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-771a-9072-08d3b519ddc9', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-07 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7478-acea-e0ca094d4c93', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-24 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7394-b794-4d54f1c8ade0', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-05-25 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7f0b-adee-f22d0c9afac6', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-05-27 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7580-bafa-357f4d9d52fd', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-12 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7d67-aa39-4d69961e62bd', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-20 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7704-b695-526616a7515d', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-20 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7f32-bbb6-ee5db51be320', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-16 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-70c0-85d4-49f8d7bef08c', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-04 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-70cd-a2cf-72f26cfbc97b', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-05-22 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-71e0-b861-0efde53b68b1', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-03 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-71bb-9069-ccbc72581bb8', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-02 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-764e-b8cf-8bbf5a7df25f', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-18 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7d46-9153-6d4847f8b225', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-24 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-766b-9f2d-b52c1897f268', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-05 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-7e3c-9dac-7eee1316dc64', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-06-04 00:00:00','COMPLETED',NOW(),NOW(),'테스트용 프로젝트입니다',0,100),
(UNHEX(REPLACE('0197a204-c3b4-716a-8c52-96e86a640c83', '-', '')),'테스트 프로젝트','2025-03-01 00:00:00','2025-05-22 00:00:00','IN_PROGRESS',NOW(),NOW(),'테스트용 프로젝트입니다',0,1200);

-- 회사에 할당된 프로젝트 생성 18건

INSERT INTO project_assign (created_at, client_company_id, dev_company_id, id, project_id) VALUES
(NOW(), UNHEX(REPLACE('9df5310b-43ec-4592-98eb-6ab2939496af', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('ae11b710-c32f-4c6f-8d49-22d207698d17', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7478-acea-e0ca094d4c93', '-', ''))),
(NOW(), UNHEX(REPLACE('4d86b3b2-c94b-49e7-8c57-e071525fc446', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('b62f2ee8-0add-472b-bfb4-600d5c2680a8', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7d46-9153-6d4847f8b225', '-', ''))),
(NOW(), UNHEX(REPLACE('a284d017-787e-4a41-ac61-43f1bcd135dc', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('fc517956-fab2-4f88-8ee8-3d61237d80b2', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7d67-aa39-4d69961e62bd', '-', ''))),
(NOW(), UNHEX(REPLACE('3651348b-ac50-4012-b9be-64ef766e0984', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('743e4b9a-886f-40a4-ac03-1a4d2e5899bd', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7704-b695-526616a7515d', '-', ''))),
(NOW(), UNHEX(REPLACE('2d918ad6-796c-4408-9237-f984c0ffe906', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('7debf202-f8aa-46db-b748-5e2cc6eb56f8', '-', '')), UNHEX(REPLACE('0197a204-c3b4-764e-b8cf-8bbf5a7df25f', '-', ''))),
(NOW(), UNHEX(REPLACE('26ac4422-b2a3-49b8-9235-7f930abeff94', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('d28a50ca-d459-4828-808b-65c106650b19', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7f32-bbb6-ee5db51be320', '-', ''))),
(NOW(), UNHEX(REPLACE('cc8fe857-96f2-491c-9f17-0044956f9274', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('d1e85cba-6104-486a-b9a6-2bc4870e7187', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7580-bafa-357f4d9d52fd', '-', ''))),
(NOW(), UNHEX(REPLACE('77e89484-1b3d-499b-89ce-cff0417bad1c', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('449d3c67-85fa-4298-acd2-ca50530b41b8', '-', '')), UNHEX(REPLACE('0197a204-c3b4-733b-81dd-9657a58599e6', '-', ''))),
(NOW(), UNHEX(REPLACE('da1a8d55-44f9-434f-a1fc-6334db71af8b', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('195a6b8c-72fb-427f-91dc-7ffb46c3e9d2', '-', '')), UNHEX(REPLACE('0197a204-c3b4-771a-9072-08d3b519ddc9', '-', ''))),
(NOW(), UNHEX(REPLACE('6f1d1bc5-f38e-4a57-ba0f-7eee3734bcb0', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('2f251dfe-908c-40c1-aca8-8f1dcb90043e', '-', '')), UNHEX(REPLACE('0197a204-c3b4-766b-9f2d-b52c1897f268', '-', ''))),
(NOW(), UNHEX(REPLACE('fbcaa8f3-cc06-4c1a-9bbd-1a3f0845c6c8', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('eca1bead-5503-48c2-9fdb-924a7eabbdf0', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7e3c-9dac-7eee1316dc64', '-', ''))),
(NOW(), UNHEX(REPLACE('d7762348-a929-4c74-af8a-c8e0fd1e373d', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('845e82d5-25dc-4539-9302-52220eb38de7', '-', '')), UNHEX(REPLACE('0197a204-c3b4-70c0-85d4-49f8d7bef08c', '-', ''))),
(NOW(), UNHEX(REPLACE('d3919352-a6fb-44fd-bf4d-8ff84b02cc76', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('8ef6baa1-996d-4cad-9ef1-cdf4868dc6b2', '-', '')), UNHEX(REPLACE('0197a204-c3b4-71e0-b861-0efde53b68b1', '-', ''))),
(NOW(), UNHEX(REPLACE('ee8f5f22-d33f-4ad4-aa80-137eb08b8993', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('f8db6dc8-f6e8-419d-8314-a1c731102967', '-', '')), UNHEX(REPLACE('0197a204-c3b4-71bb-9069-ccbc72581bb8', '-', ''))),
(NOW(), UNHEX(REPLACE('7e0fc471-d37c-4ffd-b324-8ffde419c113', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('70363c1e-88d4-438c-848c-6736d861481a', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7f0b-adee-f22d0c9afac6', '-', ''))),
(NOW(), UNHEX(REPLACE('26c6880f-3cfa-4584-9bf4-80892fb09a91', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('574f372f-eda8-43f8-9a8d-ea5c7c5765e6', '-', '')), UNHEX(REPLACE('0197a204-c3b4-7394-b794-4d54f1c8ade0', '-', ''))),
(NOW(), UNHEX(REPLACE('54e3ee8a-a054-4073-bd42-d50496a454a5', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('065339a5-b645-44c0-8527-204c3cfb92ad', '-', '')), UNHEX(REPLACE('0197a204-c3b4-70cd-a2cf-72f26cfbc97b', '-', ''))),
(NOW(), UNHEX(REPLACE('8749040c-394c-41c3-ac8e-033e483f4d53', '-', '')), UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')), UNHEX(REPLACE('57d569c4-b112-44d6-8fe5-8a12b6a06982', '-', '')), UNHEX(REPLACE('0197a204-c3b4-716a-8c52-96e86a640c83', '-', '')));
