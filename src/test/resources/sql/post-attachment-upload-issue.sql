INSERT INTO post (id, project_step_id, title, company_name, author_name, content, approval,
                  deleted, modified_at, created_at)
VALUES (UNHEX(REPLACE('019790cd-b39a-72d6-b3a3-403250b68b9e', '-', '')),
        UNHEX(REPLACE('4321a2a2-00b2-0000-c2bb-81c0000aa00c', '-', '')),
        '제목',
        '회사명',
        '작성자',
        '내용',
        '승인여부',
        FALSE,
        NOW(),
        NOW());
