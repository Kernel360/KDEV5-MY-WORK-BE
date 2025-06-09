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
    deleted
) VALUES (
             UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),                  -- 또는 '2f1a6b3e-7d0e-4ef0-8c9e-0fc0f3d5b2a3' 등 수동 UUID
             '프로젝트 이름',
             '2025-06-01 09:00:00',
             '2025-06-30 18:00:00',
             '기획 단계',
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             '이 프로젝트는 백엔드 시스템 개발을 위한 것입니다.',
             false
         );

-- 프로젝트 단계 생성
insert into project_step (order_num, created_at, id, title)
values (1, '2025-06-02 11:00:00', UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '기획');

insert into project_step (order_num, created_at, id, title)
values (2, '2025-06-02 11:10:00', UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '개발');

-- post_id 테이블에 25개 UUID 삽입
INSERT INTO post_id VALUES
    (UNHEX(REPLACE('019739ca-e6ad-7166-b87b-2bd4886f55d7', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7751-85fb-0f9f8bc7e742', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-72ee-9bdd-1b88fac0bb08', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7018-a2f2-d21456485685', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-76a0-aff6-48f13445d6c8', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7c39-bf5b-4791155e938b', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7d30-aaed-24a437fc2ea3', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7234-9c6a-6a4735a8bddc', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-76b1-9f27-244bf13ab263', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-717e-8fe1-19aa3400ea9d', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-73b6-a247-2c5ad94c29b3', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7406-ae0f-b2a5cefd4569', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-72f6-9f30-6fa46e733c06', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7fb2-8441-d3417c402491', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7b88-b393-fe30946e64ee', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7f20-9aa8-6f79275670a8', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-77cb-8c4b-2f38c456eb3f', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7e84-84c7-158621d10c3b', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-71ea-a780-64b15e3ea214', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7f7d-a35e-e5608fe0e141', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-795d-99c1-23e7fe40f3dd', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7702-adfc-79faaddde3c4', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7420-b8ec-be8659db84e6', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-73c4-9b35-e35a81d2390d', '-', ''))),
    (UNHEX(REPLACE('019739ca-e6ad-7453-9008-b2dc67b222a3', '-', '')));

-- post 테이블에 25개 레코드 삽입 (project_step_id는 두 종류로 삽입)
INSERT INTO post (id, project_id, project_step_id, title, company_name, author_name, content, approval, deleted, modified_at, created_at) VALUES
  (UNHEX(REPLACE('019739ca-e6ad-7166-b87b-2bd4886f55d7', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목1', '회사명1', '작성자1', '내용1', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7751-85fb-0f9f8bc7e742', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목2', '회사명2', '작성자2', '내용2', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-72ee-9bdd-1b88fac0bb08', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목3', '회사명3', '작성자3', '내용3', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7018-a2f2-d21456485685', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목4', '회사명4', '작성자4', '내용4', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-76a0-aff6-48f13445d6c8', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목5', '회사명5', '작성자5', '내용5', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7c39-bf5b-4791155e938b', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목6', '회사명6', '작성자6', '내용6', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7d30-aaed-24a437fc2ea3', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목7', '회사명7', '작성자7', '내용7', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7234-9c6a-6a4735a8bddc', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목8', '회사명8', '작성자8', '내용8', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-76b1-9f27-244bf13ab263', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목9', '회사명9', '작성자9', '내용9', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-717e-8fe1-19aa3400ea9d', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목10', '회사명10', '작성자10', '내용10', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-73b6-a247-2c5ad94c29b3', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목11', '회사명11', '작성자11', '내용11', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7406-ae0f-b2a5cefd4569', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d2-2e80-709f-a9c5-7da758c956d1', '-', '')), '제목12', '회사명12', '작성자12', '내용12', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-72f6-9f30-6fa46e733c06', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목13', '회사명13', '작성자13', '내용13', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7fb2-8441-d3417c402491', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목14', '회사명14', '작성자14', '내용14', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7b88-b393-fe30946e64ee', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목15', '회사명15', '작성자15', '내용15', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7f20-9aa8-6f79275670a8', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목16', '회사명16', '작성자16', '내용16', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-77cb-8c4b-2f38c456eb3f', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목17', '회사명17', '작성자17', '내용17', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7e84-84c7-158621d10c3b', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목18', '회사명18', '작성자18', '내용18', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-71ea-a780-64b15e3ea214', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목19', '회사명19', '작성자19', '내용19', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7f7d-a35e-e5608fe0e141', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목20', '회사명20', '작성자20', '내용20', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-795d-99c1-23e7fe40f3dd', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목21', '회사명21', '작성자21', '내용21', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7702-adfc-79faaddde3c4', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목22', '회사명22', '작성자22', '내용22', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7420-b8ec-be8659db84e6', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목23', '회사명23', '작성자23', '내용23', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-73c4-9b35-e35a81d2390d', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목24', '회사명24', '작성자24', '내용24', '승인여부', FALSE, NOW(), NOW()),
  (UNHEX(REPLACE('019739ca-e6ad-7453-9008-b2dc67b222a3', '-', '')), UNHEX(REPLACE('01975454-e57b-7df5-acb8-598c64aaf54e', '-', '')),UNHEX(REPLACE('019739d7-54f3-7807-b0c0-d8017fc30c5a', '-', '')), '제목25', '회사명25', '작성자25', '내용25', '승인여부', FALSE, NOW(), NOW());

