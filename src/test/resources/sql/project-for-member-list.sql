-- 1) 공통으로 사용할 회사 UUID (버전 7 예시)
SET
@company_id = 'a62a0c20-91e2-7c2d-b0e5-e16115c61888';

-- 2) 공통으로 사용할 프로젝트 UUID (버전 7 예시)
SET
@project_id = 'd73b1f10-47e2-7a2d-c1e5-f17125d62999';

-- 멤버 1 (아직 프로젝트 미할당)
INSERT INTO member (id,
                    company_id,
                    name,
                    department,
                    position,
                    role,
                    phone_number,
                    email,
                    password,
                    deleted,
                    birth_date,
                    created_at,
                    modified_at)
VALUES (UNHEX(REPLACE('01973a41-78ed-71c4-b4a6-8d0d5d67f1d9', '-', '')),
        UNHEX(REPLACE(@company_id, '-', '')),
        'Member One',
        'Development',
        'Engineer',
        'USER',
        '010-1111-1111',
        'member.one@unique.com',
        'password1',
        0,
        '1990-01-01 00:00:00',
        NOW(),
        NOW());

-- 멤버 2 (아직 프로젝트 미할당)
INSERT INTO member (id,
                    company_id,
                    name,
                    department,
                    position,
                    role,
                    phone_number,
                    email,
                    password,
                    deleted,
                    birth_date,
                    created_at,
                    modified_at)
VALUES (UNHEX(REPLACE('01973a41-dc5f-7aa0-90d1-819537a65c02', '-', '')),
        UNHEX(REPLACE(@company_id, '-', '')),
        'Member Two',
        'Marketing',
        'Manager',
        'USER',
        '010-2222-2222',
        'member.two@unique.com',
        'password2',
        0,
        '1991-02-02 00:00:00',
        NOW(),
        NOW());

-- 멤버 3 (이 멤버는 project_member에 할당할 예정)
INSERT INTO member (id,
                    company_id,
                    name,
                    department,
                    position,
                    role,
                    phone_number,
                    email,
                    password,
                    deleted,
                    birth_date,
                    created_at,
                    modified_at)
VALUES (UNHEX(REPLACE('01973a42-0995-74aa-9298-a25cb8dae6ef', '-', '')),
        UNHEX(REPLACE(@company_id, '-', '')),
        'Member Three',
        'Sales',
        'Associate',
        'USER',
        '010-3333-3333',
        'member.three@unique.com',
        'password3',
        0,
        '1992-03-03 00:00:00',
        NOW(),
        NOW());

-- 멤버 4 (아직 프로젝트 미할당)
INSERT INTO member (id,
                    company_id,
                    name,
                    department,
                    position,
                    role,
                    phone_number,
                    email,
                    password,
                    deleted,
                    birth_date,
                    created_at,
                    modified_at)
VALUES (UNHEX(REPLACE('01973a42-2a6b-7aa2-aa6f-27242bd2aaf9', '-', '')),
        UNHEX(REPLACE(@company_id, '-', '')),
        'Member Four',
        'HR',
        'Specialist',
        'USER',
        '010-4444-4444',
        'member.four@unique.com',
        'password4',
        0,
        '1993-04-04 00:00:00',
        NOW(),
        NOW());

-- 멤버 5 (아직 프로젝트 미할당)
INSERT INTO member (id,
                    company_id,
                    name,
                    department,
                    position,
                    role,
                    phone_number,
                    email,
                    password,
                    deleted,
                    birth_date,
                    created_at,
                    modified_at)
VALUES (UNHEX(REPLACE('01973a42-7183-73af-a1ac-c8298c36961d', '-', '')),
        UNHEX(REPLACE(@company_id, '-', '')),
        'Member Five',
        'Finance',
        'Analyst',
        'USER',
        '010-5555-5555',
        'member.five@unique.com',
        'password5',
        0,
        '1994-05-05 00:00:00',
        NOW(),
        NOW());

INSERT INTO project_member (id,
                            project_id,
                            member_id,
                            manager,
                            deleted,
                            created_at,
                            modified_at)
VALUES (UNHEX(REPLACE('01933a42-b752-722c-bce7-d2e8c40b3509', '-', '')),
        UNHEX(REPLACE(@project_id, '-', '')),
        UNHEX(REPLACE('01973a42-2a6b-7aa2-aa6f-27242bd2aaf9', '-', '')), -- 멤버 3 ID
        0, -- manager 여부 (0: 일반, 1: 매니저)
        0, -- deleted 플래그 (0: 활성, 1: 삭제됨)
        NOW(),
        NOW());
