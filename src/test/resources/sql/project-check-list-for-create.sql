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
