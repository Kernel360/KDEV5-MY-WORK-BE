INSERT INTO post (id, project_step_id, title, company_name, author_name, content, approval,
                  deleted, modified_at, created_at)
VALUES (UUID_TO_BIN('019790da-3d89-7d84-b5e1-b5bb8109dc02'),
        UUID_TO_BIN('4321a2a2-00b2-0000-c2bb-81c0000aa00c'),
        '제목',
        '회사명',
        '작성자',
        '내용',
        '승인여부',
        FALSE,
        NOW(),
        NOW());

insert into post_attachment (id, post_id, path, active, deleted, created_at, modified_at)
values (UUID_TO_BIN('019790db-3830-768d-83ea-a57eeee6bbfc'),
        UUID_TO_BIN('019790da-3d89-7d84-b5e1-b5bb8109dc02'),
        'mywork-image.jpg',
        FALSE,
        FALSE,
        '2025-06-21 14:00:00',
        '2025-06-21 14:00:00');
