-- 1) 테스트용 회사
INSERT INTO company (
    id, name, contact_phone_number, business_number,
    address, contact_email, detail, type,
    deleted, created_at, modified_at, logo_image_path
) VALUES (
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '테스트회사','010-0000-0000','000-00-00000',
             '테스트주소','test@company.com','테스트용 회사','CLIENT',
             0,NOW(),NOW(),NULL
         );

-- 2) 멤버 1
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('01971bc8-eb4e-7ef2-8b11-8492fd9fe84a','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '김철수','개발팀','사원',
             'USER','010-1234-5678','chulsoo@example.com','pass1',0
         );

-- 3) 멤버 2
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('01971bc9-258d-7806-b5df-0fd9841c7443','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '이영희','기획팀','매니저',
             'ADMIN','010-8765-4321','younghee@example.com','pass2',0
         );
