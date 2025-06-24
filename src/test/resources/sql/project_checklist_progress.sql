INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES (UUID_TO_BIN('0196f7a6-10b6-7123-a2dc-32c3861ea55e'),
        '고객사 개발 프로젝트',
        NOW(),
        NOW(),
        'IN_PROGRESS',
        NOW(),
        NOW(),
        '고객사의 웹페이지를 구성해주는 프로젝트입니다',
        0,
        0);


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

-- 기획 단계 체크리스트 (10개)
-- 7개 APPROVED, 1개 PENDING, 1개 REJECTED, 1개 UPDATE_REQUEST
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at, deleted)
VALUES
-- APPROVED (7개)
(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111111'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '사업 목표 및 범위 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111112'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '타겟 사용자 분석', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111113'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '기능 요구사항 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111114'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '비기능 요구사항 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111115'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '사이트맵 구성', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111116'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '사용자 플로우 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111117'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '와이어프레임 작성', 'APPROVED', NOW(), 0),

-- PENDING (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111118'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '기술 스택 선정', 'PENDING', NOW(), 0),

-- REJECTED (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8aaa-111111111119'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '예산 계획 수립', 'REJECTED', NOW(), 0),

-- UPDATE_REQUEST (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8aaa-11111111111a'),
 UUID_TO_BIN('01991f58-8a6a-7a18-8cfe-1f2bfa6a5e01'),
 '일정 계획 수립', 'UPDATE_REQUEST', NOW(), 0);

-- 디자인 단계 체크리스트 (10개)
-- 7개 APPROVED, 1개 PENDING, 1개 REJECTED, 1개 UPDATE_REQUEST
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at,
                                deleted)
VALUES
-- APPROVED (7개)
(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222221'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '브랜드 가이드라인 수립', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222222'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '컬러 팔레트 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222223'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '타이포그래피 설정', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222224'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '아이콘 및 이미지 스타일 정의', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222225'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '메인 페이지 디자인', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222226'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '서브 페이지 디자인', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222227'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 'UI 컴포넌트 설계', 'APPROVED', NOW(), 0),

-- PENDING (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222228'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '반응형 디자인 검토', 'PENDING', NOW(), 0),

-- REJECTED (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8bbb-222222222229'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '디자인 시스템 문서화', 'REJECTED', NOW(), 0),

-- UPDATE_REQUEST (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8bbb-22222222222a'),
 UUID_TO_BIN('01991f59-2acb-7a72-a64f-5e1a257bbbe2'),
 '프로토타입 제작', 'UPDATE_REQUEST', NOW(), 0);

-- 개발 단계 체크리스트 (10개)
-- 7개 APPROVED, 1개 PENDING, 1개 REJECTED, 1개 UPDATE_REQUEST
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at, deleted)
VALUES
-- APPROVED (7개)
(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333331'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '개발 환경 구축', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333332'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '데이터베이스 설계', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333333'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 'API 설계 및 구현', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333334'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '프론트엔드 개발', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333335'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '백엔드 개발', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333336'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '단위 테스트 작성', 'APPROVED', NOW(), 0),

(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333337'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '통합 테스트', 'APPROVED', NOW(), 0),

-- PENDING (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333338'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '성능 최적화', 'PENDING', NOW(), 0),

-- REJECTED (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8ccc-333333333339'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '보안 검토', 'REJECTED', NOW(), 0),

-- UPDATE_REQUEST (1개)
(UUID_TO_BIN('01991f60-1234-7a11-8ccc-33333333333a'),
 UUID_TO_BIN('01991f59-6ecf-7a2a-8bb4-92707f10cc0c'),
 '배포 및 운영 준비', 'UPDATE_REQUEST', NOW(), 0);
