INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES
    (UNHEX(REPLACE('0197893a-ad34-734c-97d7-2e0dd6429247', '-', '')), 'ERP 시스템', NOW(), NOW(), '기획', NOW(), NOW(), 'ERP 프로젝트입니다.', 0);

INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES
-- ERP
(UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), UNHEX(REPLACE('0197893a-ad34-734c-97d7-2e0dd6429247', '-', '')), '기획', 1, NOW());

-- ERP: 게시글 5개
INSERT INTO post (id, project_step_id, title, company_name, author_name, content, created_at, modified_at, approval, deleted) VALUES
(UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), 'ERP 글1', 'ERP Corp', '홍길동', '내용입니다.', NOW(), NOW(), '대기', 0),
(UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), 'ERP 글2', 'ERP Corp', '홍길동', '내용입니다.', NOW(), NOW(), '대기', 0),
(UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), 'ERP 글3', 'ERP Corp', '홍길동', '내용입니다.', NOW(), NOW(), '대기', 0),
(UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), 'ERP 글4', 'ERP Corp', '홍길동', '내용입니다.', NOW(), NOW(), '대기', 0),
(UNHEX(REPLACE(UUID(), '-', '')), UNHEX(REPLACE('0197893a-ad34-7585-b6b8-ce4336b688ad', '-', '')), 'ERP 글5', 'ERP Corp', '홍길동', '내용입니다.', NOW(), NOW(), '대기', 0);