-- 프로젝트 1
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES (
           UUID_TO_BIN('11111111-1111-1111-1111-111111111111'),
           '다건삭제프로젝트1',
           NOW(),
           NOW(),
           'IN_PROGRESS',
           NOW(),
           NOW(),
           '다건 삭제 테스트용 프로젝트1',
           0
       );

-- 프로젝트 2
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES (
           UUID_TO_BIN('22222222-2222-2222-2222-222222222222'),
           '다건삭제프로젝트2',
           NOW(),
           NOW(),
           'IN_PROGRESS',
           NOW(),
           NOW(),
           '다건 삭제 테스트용 프로젝트2',
           0
       );
