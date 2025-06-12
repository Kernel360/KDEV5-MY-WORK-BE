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
                     contact_phone_number, contact_email, logo_image_path,
                     created_at, modified_at, deleted)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '마이워크',
        '업무 관리 솔루션 회사입니다.',
        '123-45-67890',
        '서울시 강남구 테헤란로 123',
        'SYSTEM',
        '010-1234-5678',
        'info@mywork.com',
        '/images/logo.png',
        NOW(), NOW(), FALSE);



-- =====================================================
-- MYWORK 프로젝트 샘플 데이터 INSERT 쿼리
-- =====================================================

-- 1. Company ID 테이블 (회사 ID 사전 발급)
INSERT INTO company_id (id)
VALUES (UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', ''))),
       (UNHEX(REPLACE('0197539a-99f1-7466-aed6-67f98bfbd07f', '-', ''))),
       (UNHEX(REPLACE('0197539a-99f1-76f0-acd3-e0803f82adc3', '-', ''))),
       (UNHEX(REPLACE('0197539a-99f1-79c3-8f7b-e310eb6b9dcb', '-', ''))),
       (UNHEX(REPLACE('0197539a-99f1-75d8-8c0d-0ef857c9c9a7', '-', '')));

-- 2. Company 테이블 (회사 정보)
INSERT INTO company (id, name, detail, business_number, address, type, contact_phone_number,
                     contact_email, logo_image_path, created_at, modified_at, deleted)
VALUES
-- DEV 회사들
(UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', '')),
 'TechCorp Ltd', '최신 기술로 혁신하는 개발 회사입니다', '123-45-67811',
 '서울시 강남구 테헤란로 427', 'DEV', '02-555-1234',
 'contact@techcorp.com', '/images/techcorp_logo.png', NOW(), NOW(), false),

(UNHEX(REPLACE('0197539a-99f1-7466-aed6-67f98bfbd07f', '-', '')),
 'InnoSoft Solutions', '사용자 중심의 소프트웨어 개발 전문 기업', '234-56-78901',
 '서울시 서초구 서초대로 74길 33', 'DEV', '02-555-2345',
 'info@innosoft.co.kr', '/images/innosoft_logo.png', NOW(), NOW(), false),

(UNHEX(REPLACE('0197539a-99f1-76f0-acd3-e0803f82adc3', '-', '')),
 'DigitalEdge Inc', '디지털 트랜스포메이션 전문 기업', '345-67-89012',
 '경기도 성남시 분당구 판교로 289', 'DEV', '031-555-3456',
 'hello@digitaledge.com', '/images/digitaledge_logo.png', NOW(), NOW(), false),

-- CLIENT 회사들
(UNHEX(REPLACE('0197539a-99f1-79c3-8f7b-e310eb6b9dcb', '-', '')),
 'GreenLife Corp', '친환경 제품을 생산하는 제조업체', '456-78-90123',
 '부산시 해운대구 마린시티 2로 38', 'CLIENT', '051-555-4567',
 'contact@greenlife.co.kr', '/images/greenlife_logo.png', NOW(), NOW(), false),

(UNHEX(REPLACE('0197539a-99f1-75d8-8c0d-0ef857c9c9a7', '-', '')),
 'SmartRetail Group', '스마트 리테일 솔루션 도입을 추진하는 유통업체', '567-89-01234',
 '대구시 수성구 달구벌대로 2423', 'CLIENT', '053-555-5678',
 'info@smartretail.com', '/images/smartretail_logo.png', NOW(), NOW(), false);

-- 3. Member 테이블 (회원 정보)
INSERT INTO member (id, company_id, name, department, position, role, phone_number, email, password,
                    deleted, created_at, modified_at, birth_date)
VALUES
-- TechCorp 직원들
(UNHEX(REPLACE('0197539a-99f1-71ce-b445-b3f4d26c508a', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', '')),
 '김시스템', 'IT관리팀', 'CTO', 'SYSTEM_ADMIN', '010-1111-1111',
 'kim.system@techcorp.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1985-03-15'),

(UNHEX(REPLACE('0197539a-99f1-7287-851e-294c23faebf9', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', '')),
 '이개발', '개발1팀', '팀장', 'DEV_ADMIN', '010-2222-2222',
 'lee.dev@techcorp.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1988-07-22'),

(UNHEX(REPLACE('0197539a-99f1-70f1-98c2-ea7dd6e3ff3b', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', '')),
 '박프론트', '개발1팀', '선임개발자', 'USER', '010-3333-3333',
 'park.frontend@techcorp.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1991-12-03'),

(UNHEX(REPLACE('0197539a-99f1-7bda-936f-cedf669df144', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7ce0-9f36-e571c06d695c', '-', '')),
 '최백엔드', '개발2팀', '주임개발자', 'USER', '010-4444-4444',
 'choi.backend@techcorp.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1993-05-18'),

-- InnoSoft 직원들
(UNHEX(REPLACE('0197539a-99f1-7c0d-b6eb-4a277e446bde', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7466-aed6-67f98bfbd07f', '-', '')),
 '정관리자', '기술기획팀', '부장', 'DEV_ADMIN', '010-5555-5555',
 'jung.admin@innosoft.co.kr', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1983-11-09'),

(UNHEX(REPLACE('0197539a-99f1-709f-91d4-dab995fc847a', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7466-aed6-67f98bfbd07f', '-', '')),
 '강풀스택', '솔루션개발팀', '차장', 'USER', '010-6666-6666',
 'kang.fullstack@innosoft.co.kr', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1986-02-27'),

-- GreenLife 직원들
(UNHEX(REPLACE('0197539a-99f1-7be8-80cf-d4e7e7d55f2c', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-79c3-8f7b-e310eb6b9dcb', '-', '')),
 '황클라이언트', 'IT기획팀', '팀장', 'CLIENT_ADMIN', '010-7777-7777',
 'hwang.client@greenlife.co.kr', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1984-09-14'),

(UNHEX(REPLACE('0197539a-99f1-7540-9846-cb2a2842a6b6', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-79c3-8f7b-e310eb6b9dcb', '-', '')),
 '윤사용자', '운영팀', '대리', 'USER', '010-8888-8888',
 'yoon.user@greenlife.co.kr', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1992-06-30'),

-- SmartRetail 직원들
(UNHEX(REPLACE('0197539a-99f1-71d7-aa7d-51c9af4c858f', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-75d8-8c0d-0ef857c9c9a7', '-', '')),
 '조클라이언트', 'IT전략팀', '부장', 'CLIENT_ADMIN', '010-9999-9999',
 'cho.client@smartretail.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1981-04-11'),

(UNHEX(REPLACE('0197539a-99f1-7204-a14e-f508329e093c', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-75d8-8c0d-0ef857c9c9a7', '-', '')),
 '임일반', '유통운영팀', '과장', 'USER', '010-0000-1111',
 'lim.general@smartretail.com', '$2a$12$pXDVZTFpcJ9ZHb1ZuFe7KeeL5hYC2YZYaQKrjZ.BlfNVePlzPwkcO',
 false, NOW(), NOW(), '1989-08-25');

-- 4. Project 테이블 (프로젝트 정보)
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES (UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
        'GreenLife 친환경 ERP 시스템 구축', '2024-01-15 09:00:00', '2024-06-30 18:00:00',
        '개발진행', NOW(), NOW(),
        '친환경 제품 생산 및 유통 관리를 위한 통합 ERP 시스템 개발 프로젝트', false),

       (UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
        'SmartRetail 옴니채널 플랫폼 개발', '2024-02-01 09:00:00', '2024-08-31 18:00:00',
        '기획완료', NOW(), NOW(),
        '온라인/오프라인 통합 리테일 플랫폼 구축을 위한 개발 프로젝트', false),

       (UNHEX(REPLACE('0197539a-99f1-7f67-98a1-961ba7fe6087', '-', '')),
        'TechCorp 내부 업무관리 시스템', '2024-03-01 09:00:00', '2024-05-31 18:00:00',
        '테스트', NOW(), NOW(),
        'TechCorp 사내 업무 관리 및 협업 도구 개발', false);

-- 5. Project Step 테이블 (프로젝트 단계)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES
-- GreenLife ERP 프로젝트 단계들
(UNHEX(REPLACE('0197539a-99f1-727f-ba28-8ca7246dbd0a', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 '요구사항 분석', 1, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7305-860b-e1b63c0c718a', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 '시스템 설계', 2, NOW()),

(UNHEX(REPLACE('0197539a-99f1-79df-8be3-bfed3d4485de', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 '개발', 3, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7f72-8fc1-3a215db5d931', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 '테스트', 4, NOW()),

-- SmartRetail 플랫폼 프로젝트 단계들
(UNHEX(REPLACE('0197539a-99f1-7790-b036-fb20f8b349f4', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 '기획', 1, NOW()),

(UNHEX(REPLACE('0197539a-99f1-74d0-b7fd-d91311a92ce3', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 'UI/UX 설계', 2, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7596-8966-21ca4d90fe43', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 '프론트엔드 개발', 3, NOW()),

-- TechCorp 내부 시스템 프로젝트 단계들
(UNHEX(REPLACE('0197539a-99f1-7590-8a4f-6915a25856a7', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7f67-98a1-961ba7fe6087', '-', '')),
 '분석', 1, NOW()),

(UNHEX(REPLACE('0197539a-99f1-74c2-9a44-571f8552d902', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7f67-98a1-961ba7fe6087', '-', '')),
 '개발', 2, NOW());

-- 6. Project Member 테이블 (프로젝트 참여자)
INSERT INTO project_member (id, project_id, member_id, manager, deleted, created_at)
VALUES
-- GreenLife ERP 프로젝트 팀
(UNHEX(REPLACE('0197539a-99f1-7848-aed6-222a071b49a7', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7287-851e-294c23faebf9', '-', '')),
 true, false, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7c86-b6d5-29faa4024df0', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-70f1-98c2-ea7dd6e3ff3b', '-', '')),
 false, false, NOW()),

(UNHEX(REPLACE('0197539a-99f1-78cf-8cbf-c7263f504b8e', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7bda-936f-cedf669df144', '-', '')),
 false, false, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7a9f-80dd-a437631e1665', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-738a-a17b-5ce91f8e570b', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7be8-80cf-d4e7e7d55f2c', '-', '')),
 false, false, NOW()),

-- SmartRetail 플랫폼 프로젝트 팀
(UNHEX(REPLACE('0197539a-99f1-7a57-b349-f27e1c94102c', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7c0d-b6eb-4a277e446bde', '-', '')),
 true, false, NOW()),

(UNHEX(REPLACE('0197539a-99f1-7e85-a479-540d272210d1', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-709f-91d4-dab995fc847a', '-', '')),
 false, false, NOW()),

(UNHEX(REPLACE('0197539a-99f1-711b-8cca-d647cb1e158d', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7b40-9259-38fa0b9fa070', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-71d7-aa7d-51c9af4c858f', '-', '')),
 false, false, NOW());

-- 7. Project Checklist 테이블 (프로젝트 체크리스트)
INSERT INTO project_check_list (id, title, project_step_id, approval, created_at, deleted)
VALUES
-- GreenLife ERP 요구사항 분석 단계 체크리스트
(UNHEX(REPLACE('0197539a-99f1-72ee-84ee-97bf7d8ad655', '-', '')),
 '비즈니스 요구사항 정의서 작성',
 UNHEX(REPLACE('0197539a-99f1-727f-ba28-8ca7246dbd0a', '-', '')),
 'PENDING', NOW(), false),

(UNHEX(REPLACE('0197539a-99f1-73b5-b2a2-8e40c4ef24fb', '-', '')),
 '기술 요구사항 분석 보고서',
 UNHEX(REPLACE('0197539a-99f1-727f-ba28-8ca7246dbd0a', '-', '')),
 'APPROVED', NOW(), false),

-- GreenLife ERP 시스템 설계 단계 체크리스트
(UNHEX(REPLACE('0197539a-99f1-7d09-b508-1477dc3783fe', '-', '')),
 'DB 설계서 검토',
 UNHEX(REPLACE('0197539a-99f1-7305-860b-e1b63c0c718a', '-', '')),
 'APPROVED', NOW(), false),

-- SmartRetail 기획 단계 체크리스트
(UNHEX(REPLACE('0197539a-99f1-7b09-bc8c-736c25309a40', '-', '')),
 '사용자 스토리 작성',
 UNHEX(REPLACE('0197539a-99f1-7790-b036-fb20f8b349f4', '-', '')),
 'PENDING', NOW(), false),

(UNHEX(REPLACE('0197539a-99f1-765b-aaa3-e1fd945e5b26', '-', '')),
 '화면 플로우 설계',
 UNHEX(REPLACE('0197539a-99f1-7790-b036-fb20f8b349f4', '-', '')),
 'REJECTED', NOW(), false);

-- 8. Post 테이블 (게시물)
INSERT INTO post (id, project_step_id, title, company_name, author_name, content,
                  approval, deleted, modified_at, created_at)
VALUES (UNHEX(REPLACE('0197539a-99f1-79fd-ab99-b5c1e3e48a58', '-', '')),
        UNHEX(REPLACE('0197539a-99f1-727f-ba28-8ca7246dbd0a', '-', '')),
        '요구사항 분석 진행 상황 보고', 'TechCorp Ltd', '이개발',
        'GreenLife ERP 시스템의 요구사항 분석이 순조롭게 진행되고 있습니다. 주요 비즈니스 프로세스 파악이 완료되었으며, 다음 주까지 상세 요구사항 정의서를 완성할 예정입니다.',
        'APPROVED', false, NOW(), NOW()),

       (UNHEX(REPLACE('0197539a-99f1-7ca6-ad2f-0a264565bd5e', '-', '')),
        UNHEX(REPLACE('0197539a-99f1-7305-860b-e1b63c0c718a', '-', '')),
        'DB 스키마 설계 완료 알림', 'TechCorp Ltd', '최백엔드',
        '친환경 제품 관리를 위한 데이터베이스 스키마 설계가 완료되었습니다. 제품 분류, 재고 관리, 생산 이력 추적 등의 핵심 기능들이 반영되었습니다.',
        'PENDING', false, NOW(), NOW()),

       (UNHEX(REPLACE('0197539a-99f1-7144-9725-2d6ab40c27be', '-', '')),
        UNHEX(REPLACE('0197539a-99f1-7790-b036-fb20f8b349f4', '-', '')),
        '옴니채널 플랫폼 기획서 1차 완성', 'InnoSoft Solutions', '정관리자',
        '온라인/오프라인 통합 채널 전략 및 고객 여정 맵핑이 포함된 기획서가 완성되었습니다. 클라이언트 검토 후 피드백을 반영하여 최종본을 완성할 예정입니다.',
        'APPROVED', false, NOW(), NOW()),

       (UNHEX(REPLACE('0197539a-99f1-7e94-81ab-5baed9534781', '-', '')),
        UNHEX(REPLACE('0197539a-99f1-7590-8a4f-6915a25856a7', '-', '')),
        '내부 업무관리 시스템 요구사항 정리', 'TechCorp Ltd', '박프론트',
        '사내 구성원들의 요구사항을 수집하여 정리하였습니다. 프로젝트 관리, 일정 공유, 문서 관리 기능이 핵심으로 파악되었습니다.',
        'REJECTED', false, NOW(), NOW());

-- 9. Review 테이블 (댓글)
INSERT INTO review (id, post_id, parent_id, member_id, comment, company_name, author_name,
                    deleted, created_at, modified_at)
VALUES
-- 첫 번째 게시물에 대한 댓글들
(UNHEX(REPLACE('0197539a-99f1-78ed-bdc7-5a76a1333185', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-79fd-ab99-b5c1e3e48a58', '-', '')),
 NULL,
 UNHEX(REPLACE('0197539a-99f1-7be8-80cf-d4e7e7d55f2c', '-', '')),
 '좋은 진행 상황입니다. 클라이언트 관점에서 몇 가지 추가 요구사항이 있어 별도로 전달드리겠습니다.',
 'GreenLife Corp', '황클라이언트', false, NOW(), NOW()),

(UNHEX(REPLACE('0197539a-99f1-7026-9344-06a79c4aafff', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-79fd-ab99-b5c1e3e48a58', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-78ed-bdc7-5a76a1333185', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7287-851e-294c23faebf9', '-', '')),
 '네, 추가 요구사항 검토 후 반영하겠습니다. 언제까지 전달해주시면 될까요?',
 'TechCorp Ltd', '이개발', false, NOW(), NOW()),

-- 두 번째 게시물에 대한 댓글
(UNHEX(REPLACE('0197539a-99f1-7739-ad26-ad00c4ca063f', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7ca6-ad2f-0a264565bd5e', '-', '')),
 NULL,
 UNHEX(REPLACE('0197539a-99f1-70f1-98c2-ea7dd6e3ff3b', '-', '')),
 '스키마 설계 수고하셨습니다. 성능 최적화 관련해서도 고려해주셨나요?',
 'TechCorp Ltd', '박프론트', false, NOW(), NOW()),

-- 세 번째 게시물에 대한 댓글들
(UNHEX(REPLACE('0197539a-99f1-7313-9189-b068cad4bc6a', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7144-9725-2d6ab40c27be', '-', '')),
 NULL,
 UNHEX(REPLACE('0197539a-99f1-71d7-aa7d-51c9af4c858f', '-', '')),
 '기획서 내용이 매우 상세하고 좋습니다. 몇 가지 비즈니스 요구사항 추가가 필요할 것 같아요.',
 'SmartRetail Group', '조클라이언트', false, NOW(), NOW()),

(UNHEX(REPLACE('0197539a-99f1-7ca3-84f9-5962ed9b7c36', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7144-9725-2d6ab40c27be', '-', '')),
 NULL,
 UNHEX(REPLACE('0197539a-99f1-709f-91d4-dab995fc847a', '-', '')),
 '클라이언트 피드백 반영을 위한 회의 일정을 잡겠습니다.',
 'InnoSoft Solutions', '강풀스택', false, NOW(), NOW()),

-- 네 번째 게시물에 대한 댓글
(UNHEX(REPLACE('0197539a-99f1-7e9c-a2f7-b99fe82b6f93', '-', '')),
 UNHEX(REPLACE('0197539a-99f1-7e94-81ab-5baed9534781', '-', '')),
 NULL,
 UNHEX(REPLACE('0197539a-99f1-7287-851e-294c23faebf9', '-', '')),
 '요구사항 정리 잘 해주셨네요. 기술적 구현 방안도 함께 검토해보겠습니다.',
 'TechCorp Ltd', '이개발', false, NOW(), NOW());
