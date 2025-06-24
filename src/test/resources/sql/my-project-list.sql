--- 프로젝트 데이터
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES
    (UNHEX(REPLACE('01975f5b-b0c0-796f-b15a-cbbeedabe1e3','-','')),
     '고객사 개발 프로젝트01', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다01', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-723f-b41c-bc922a25f86c','-','')),
     '고객사 개발 프로젝트02', NOW(), NOW(), 'IN_PROGRESS', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다02', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7478-8517-333d362cfc6a','-','')),
     '고객사 개발 프로젝트03', NOW(), NOW(), 'PAUSED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다03', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-73c8-99d8-c7ea5aaba8d5','-','')),
     '고객사 개발 프로젝트04', NOW(), NOW(), 'COMPLETED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7c72-92b8-a46e34d4413b','-','')),
     '고객사 개발 프로젝트05', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0);

--- 프로젝트 할당 데이터
INSERT INTO project_assign (id, project_id, dev_company_id, client_company_id, created_at)
VALUES
-- Project 01
(UNHEX(REPLACE('01975f65-a5f0-78cf-b4ad-2e7bdfa3c655','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-796f-b15a-cbbeedabe1e3','-','')),
 UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7130-8d02-e52ddc218c10','-','')),
 NOW()),

-- Project 02
(UNHEX(REPLACE('01975f65-a5f0-755c-b7e0-4ba37569d13d','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-723f-b41c-bc922a25f86c','-','')),
 UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7a15-9015-84829e9c4cc7','-','')),
 NOW()),

-- Project 03
(UNHEX(REPLACE('01975f65-a5f0-7f4b-acc5-1d3f04b733b2','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7478-8517-333d362cfc6a','-','')),
 UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7c20-be94-c216eeb95e81','-','')),
 NOW()),

-- Project 04
(UNHEX(REPLACE('01975f65-a5f0-70b0-b6b0-c3fbe6a46243','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-73c8-99d8-c7ea5aaba8d5','-','')),
 UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55','-','')),
 UNHEX(REPLACE('01975f65-a5f0-790f-a811-6c8a93a2aa3f','-','')),
 NOW()),

-- Project 05
(UNHEX(REPLACE('01975f65-a5f0-781f-9d91-3099f7055c69','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7c72-92b8-a46e34d4413b','-','')),
 UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7941-b015-23b1e0c9ad68','-','')),
 NOW());