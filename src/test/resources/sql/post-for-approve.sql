-- 기획 단계 (order_num: 1)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '기획', 1, NOW());

-- 디자인 단계 (order_num: 2)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '디자인', 2, NOW());

-- 개발 단계 (order_num: 3)
INSERT INTO project_step (id, project_id, title, order_num, created_at)
VALUES (UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
        UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '개발', 3, NOW());


INSERT INTO post_id (id, created_at)
VALUES (UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')), CURRENT_TIMESTAMP);

INSERT INTO post (id,
                  project_step_id,
                  title,
                  company_name,
                  author_name,
                  content,
                  approval,
                  deleted,
                  modified_at,
                  created_at,
                  author_id
) VALUES (
             UNHEX(REPLACE('1234a9a9-90b6-9898-a9dc-92c9861aa98c', '-', '')),
             UNHEX(REPLACE('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01', '-', '')),
             '제목',
             '회사명',
             '작성자',
             '내용',
             '승인여부',
             FALSE,
             NOW(),
             NOW(),
             UNHEX(REPLACE('0197c5d8-53c8-7c2d-bffb-c05664767617', '-', ''))
         );
