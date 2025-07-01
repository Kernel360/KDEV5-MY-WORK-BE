INSERT INTO member (id, name, email, password, role, birth_date, phone_number,
                    company_id, department, position, created_at, modified_at, deleted)
VALUES
    -- system admin
    (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
     '관리자',
     'admin@example.com',
     '$2a$12$TltsHoe3g9myZdiJaNCDnezKnx6sybA1csuL9jFvFFQE8GaA3fhmK', -- 'password1234' bcrypt
     'SYSTEM_ADMIN',
     NOW(),
     '010-1234-5678',
     UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
     '개발팀',
     'CTO',
     NOW(),
     NOW(),
     false);

INSERT INTO company (id, name, detail, business_number, address, type,
                     contact_phone_number, contact_email, file_name,
                     created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '마이워크',
        '업무 관리 솔루션 회사입니다.',
        '123-45-67890',
        '서울시 강남구 테헤란로 123',
        'SYSTEM',
        '010-1234-5678',
        'info@mywork.com',
        'logo.png',
        NOW(), NOW(), FALSE);


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
                     file_name,
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
        '1234a9a9-90b6-9898-a9dc-92c9861aa98c.png',
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
        FALSE
);

-- 프로젝트 생성
INSERT INTO project (
    id,
    name,
    start_at,
    end_at,
    step,
    created_at,
    modified_at,
    detail,
    deleted,
    project_amount
) VALUES (
             UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),                  -- 또는 '2f1a6b3e-7d0e-4ef0-8c9e-0fc0f3d5b2a3' 등 수동 UUID
             '프로젝트 이름',
             '2025-06-01 09:00:00',
             '2025-06-30 18:00:00',
             '기획 단계',
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             '이 프로젝트는 백엔드 시스템 개발을 위한 것입니다.',
             false,
             0
         );

-- 프로젝트 단계 생성
insert into project_step (project_id, order_num, created_at, id, title)
values (UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')), 1, '2025-06-02 11:00:00', UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '기획');

insert into project_step (project_id, order_num, created_at, id, title)
values (UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),2, '2025-06-02 11:10:00', UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '개발');

-- post_id 테이블에 25개 UUID 삽입
INSERT INTO post_id (id, created_at) VALUES
    (UNHEX(REPLACE('019739ca-e6ad-7166-b87b-2bd4886f55d7', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7751-85fb-0f9f8bc7e742', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-72ee-9bdd-1b88fac0bb08', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7018-a2f2-d21456485685', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-76a0-aff6-48f13445d6c8', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7c39-bf5b-4791155e938b', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7d30-aaed-24a437fc2ea3', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7234-9c6a-6a4735a8bddc', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-76b1-9f27-244bf13ab263', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-717e-8fe1-19aa3400ea9d', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-73b6-a247-2c5ad94c29b3', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7406-ae0f-b2a5cefd4569', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-72f6-9f30-6fa46e733c06', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7fb2-8441-d3417c402491', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7b88-b393-fe30946e64ee', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7f20-9aa8-6f79275670a8', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-77cb-8c4b-2f38c456eb3f', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7e84-84c7-158621d10c3b', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-71ea-a780-64b15e3ea214', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7f7d-a35e-e5608fe0e141', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-795d-99c1-23e7fe40f3dd', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7702-adfc-79faaddde3c4', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7420-b8ec-be8659db84e6', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-73c4-9b35-e35a81d2390d', '-', '')), CURRENT_TIMESTAMP),
    (UNHEX(REPLACE('019739ca-e6ad-7453-9008-b2dc67b222a3', '-', '')), CURRENT_TIMESTAMP);

-- post 테이블에 25개 레코드 삽입 (project_step_id는 두 종류로 삽입)
INSERT INTO post (id, project_step_id, title, company_name, author_name, content, approval, deleted, modified_at, created_at) VALUES
  (UNHEX(REPLACE('019739ca-e6ad-7166-b87b-2bd4886f55d7', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목1', '회사명1', '작성자1', '내용1', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7751-85fb-0f9f8bc7e742', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목2', '회사명2', '작성자2', '내용2', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-72ee-9bdd-1b88fac0bb08', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목3', '회사명3', '작성자3', '내용3', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7018-a2f2-d21456485685', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목4', '회사명4', '작성자4', '내용4', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-76a0-aff6-48f13445d6c8', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목5', '회사명5', '작성자5', '내용5', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7c39-bf5b-4791155e938b', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목6', '회사명6', '작성자6', '내용6', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7d30-aaed-24a437fc2ea3', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목7', '회사명7', '작성자7', '내용7', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7234-9c6a-6a4735a8bddc', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목8', '회사명8', '작성자8', '내용8', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-76b1-9f27-244bf13ab263', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목9', '회사명9', '작성자9', '내용9', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-717e-8fe1-19aa3400ea9d', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목10', '회사명10', '작성자10', '내용10', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-73b6-a247-2c5ad94c29b3', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목11', '회사명11', '작성자11', '내용11', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7406-ae0f-b2a5cefd4569', '-', '')), UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목12', '회사명12', '작성자12', '내용12', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-72f6-9f30-6fa46e733c06', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목13', '회사명13', '작성자13', '내용13', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7fb2-8441-d3417c402491', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목14', '회사명14', '작성자14', '내용14', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7b88-b393-fe30946e64ee', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목15', '회사명15', '작성자15', '내용15', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7f20-9aa8-6f79275670a8', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목16', '회사명16', '작성자16', '내용16', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-77cb-8c4b-2f38c456eb3f', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목17', '회사명17', '작성자17', '내용17', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7e84-84c7-158621d10c3b', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목18', '회사명18', '작성자18', '내용18', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-71ea-a780-64b15e3ea214', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목19', '회사명19', '작성자19', '내용19', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7f7d-a35e-e5608fe0e141', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목20', '회사명20', '작성자20', '내용20', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-795d-99c1-23e7fe40f3dd', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목21', '회사명21', '작성자21', '내용21', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7702-adfc-79faaddde3c4', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목22', '회사명22', '작성자22', '내용22', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7420-b8ec-be8659db84e6', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목23', '회사명23', '작성자23', '내용23', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-73c4-9b35-e35a81d2390d', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목24', '회사명24', '작성자24', '내용24', 'PENDING', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7453-9008-b2dc67b222a3', '-', '')), UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목25', '회사명25', '작성자25', '내용25', 'PENDING', FALSE, NOW(), NOW());

