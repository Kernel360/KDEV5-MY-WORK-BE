-- 회사 삽입
INSERT INTO company (
    id, name, detail, business_number, address, type,
    contact_phone_number, contact_email, logo_image_path,
    created_at, modified_at, deleted
) VALUES (
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
             '마이워크',
             '업무 관리 솔루션 회사입니다.',
             '123-45-67890',
             '서울시 강남구 테헤란로 123',
             'DEV',
             '010-1234-5678',
             'info@mywork.com',
             '/images/logo.png',
             NOW(), NOW(), FALSE
         );

-- 직원 3명 (정상)
INSERT INTO member (
    id, name, department, position, role, phone_number, email, password,
    company_id, deleted, birth_date
) VALUES
      (
          UNHEX(REPLACE('11111111-1111-1111-1111-111111111111', '-', '')),
          '홍길동', '개발팀', '사원', 'USER', '010-0000-0001',
          'gildong@mywork.com', 'pw1',
          UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
          FALSE, '1990-01-01 00:00:00'
      ),
      (
          UNHEX(REPLACE('22222222-2222-2222-2222-222222222222', '-', '')),
          '이몽룡', '마케팅팀', '주임', 'USER', '010-0000-0002',
          'mongryong@mywork.com', 'pw2',
          UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
          FALSE, '1991-02-02 00:00:00'
      ),
      (
          UNHEX(REPLACE('33333333-3333-3333-3333-333333333333', '-', '')),
          '성춘향', '영업팀', '대리', 'USER', '010-0000-0003',
          'chunhyang@mywork.com', 'pw3',
          UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
          FALSE, '1992-03-03 00:00:00'
      );

-- 삭제 처리된 직원 1명
INSERT INTO member (
    id, name, department, position, role, phone_number, email, password,
    company_id, deleted, birth_date
) VALUES (
             UNHEX(REPLACE('44444444-4444-4444-4444-444444444444', '-', '')),
             '배제대상', '보안팀', '과장', 'ADMIN', '010-0000-0004',
             'deleted@mywork.com', 'pw4',
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
             TRUE, '1985-05-05 00:00:00'
         );
