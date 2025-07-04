INSERT INTO project_member (id, project_id, member_id, manager, deleted, created_at, modified_at)
VALUES
    (
        UUID_TO_BIN('01974f20-3bbe-7d0d-a27e-097667886779'),
        UUID_TO_BIN('01974f0b-5c7a-7fa2-9aba-1323490b77e9'),
        UUID_TO_BIN('019739ea-e7eb-76b7-b5e1-b9dc3ea1e9c2'),
        0,
        0,
        NOW(),
        NOW()
    );