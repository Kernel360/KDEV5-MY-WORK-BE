-- 프로젝트 
INSERT INTO project(id, name, step, detail, start_at, end_at, deleted, created_at, modified_at,project_amount)
VALUES
    (UNHEX(REPLACE('01975d7f-052c-7d8f-819b-9a08f322ead3','-','')), '금융 거래 시스템 업그레이드', 'COMPLETED', '본 프로젝트는 금융 거래 시스템 업그레이드을 통해 업무 효율을 극대화하고 사용자 경험을 향상시키는 것을 목표로 합니다.', '2025-04-26 17:58:05', '2025-10-07 19:58:05', b'0', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0),
    (UNHEX(REPLACE('01975d82-e17b-71a9-801d-b941addc79d2','-','')), '리테일 POS 시스템 개발', 'IN_PROGRESS', '본 프로젝트는 리테일 POS 시스템 개발을 통해 업무 효율을 극대화하고 사용자 경험을 향상시키는 것을 목표로 합니다.', '2025-04-12 02:15:32', '2025-07-19 05:15:32', b'0', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0);

-- 프로젝트 단계
INSERT INTO project_step(id,project_id,order_num,title,created_at)
VALUES
    (UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5','-','')),UNHEX(REPLACE('01975d7f-052c-7d8f-819b-9a08f322ead3','-','')), 1, '기획', CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea','-','')),UNHEX(REPLACE('01975d7f-052c-7d8f-819b-9a08f322ead3','-','')), 2, '분석', CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8','-','')),UNHEX(REPLACE('01975d7f-052c-7d8f-819b-9a08f322ead3','-','')), 3, '설계', CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')),UNHEX(REPLACE('01975d82-e17b-71a9-801d-b941addc79d2','-','')), 1, '기획', CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')),UNHEX(REPLACE('01975d82-e17b-71a9-801d-b941addc79d2','-','')), 2, '분석', CURRENT_TIMESTAMP(6)),
    (UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')),UNHEX(REPLACE('01975d82-e17b-71a9-801d-b941addc79d2','-','')), 3, '설계', CURRENT_TIMESTAMP(6));

-- 첫 번째 프로젝트 - 기획 단계 체크리스트 (20개)
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at, deleted)
VALUES
-- PENDING (11개)
(UNHEX(REPLACE('019763fb-3358-7781-9595-aacc94171030', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '사업 목표 정의', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7334-8901-da109014f1bf', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '타겟 고객 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7684-9a02-66f825801a71', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '경쟁사 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7fae-bd63-8b135fc3cbe6', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 범위 설정', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-773c-8ade-13f76d743bc5', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '기능 요구사항 정의', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7d74-a48d-01372aa92811', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '비기능 요구사항 정의', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7836-80cf-1c0f6168dc56', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '사용자 스토리 작성', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7266-b625-27fca2246f7e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 일정 계획', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-74a2-a46b-283af07b8ca0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '리소스 계획 수립', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7d04-8a2b-6c29f62d5b82', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '위험 요소 식별', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-78e3-a87d-7afdb07a79c8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '예산 계획 수립', 'PENDING', CURRENT_TIMESTAMP(6), 0),

-- APPROVED (3개)
(UNHEX(REPLACE('019763fb-3358-7ad4-b7fc-119490c04d49', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 팀 구성', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-71bb-b0a0-bc8a792b62c8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '이해관계자 식별', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7747-8b24-cecc70cb47b1', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '커뮤니케이션 계획', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

-- REJECTED (3개)
(UNHEX(REPLACE('019763fb-3358-7ef3-8642-92548754b18e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '기술 스택 선정', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-79dd-9912-f5340efc810f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '품질 기준 정의', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7cd4-8b92-95a2c51d2014', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '성공 기준 정의', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

-- UPDATE_REQUEST (3개)
(UNHEX(REPLACE('019763fb-3358-7374-bc16-8b462972a6a2', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '승인 프로세스 정의', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7e91-990d-bf5650cbf45d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '변경 관리 프로세스', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-72ea-b5f9-10b55ea29044', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '문서화 표준 정의', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 첫 번째 프로젝트 - 분석 단계 체크리스트 (20개)
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at, deleted)
VALUES
-- PENDING (11개)
(UNHEX(REPLACE('019763fb-3358-7b65-90d1-f7a900380612', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '현재 시스템 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3358-7ce6-a320-5bf673ea587d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '데이터 플로우 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7ed2-9933-8cac4e213278', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '사용자 행동 패턴 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7f71-bdd3-399e3f60790c', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '성능 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7e96-94cc-17a4533815c0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '보안 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7e0a-8d74-94e0685c9072', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '통합 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7880-ab6b-a3ea26fd5c96', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), 'UI/UX 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7928-a68b-8b02e379a8cc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '데이터 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7fb2-a5b0-8d487ed33d73', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '인터페이스 요구사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-74df-be4a-3c651512fa71', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '비즈니스 프로세스 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-78e9-a260-d646fb8e798f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '기술적 제약사항 분석', 'PENDING', CURRENT_TIMESTAMP(6), 0),

-- APPROVED (3개)
(UNHEX(REPLACE('019763fb-3359-7d50-87c6-76bffa89af73', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 우선순위 설정', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-77e7-b9d3-96569f6850fc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '이해관계자 요구사항 검증', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7543-973f-4bb4db2a3d3f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 추적성 매트릭스', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

-- REJECTED (3개)
(UNHEX(REPLACE('019763fb-3359-7d01-bf1e-ae511c4977de', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '레거시 시스템 영향 분석', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-700a-9d68-e7b38a7e969e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '마이그레이션 전략 분석', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-794b-8efa-187ff6b49d5d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '운영 영향도 분석', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

-- UPDATE_REQUEST (3개)
(UNHEX(REPLACE('019763fb-3359-7e0b-a5e0-eca1da1bac57', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 변경 영향도 분석', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-70d7-a72b-bc65b1ba1e34', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '기술적 실현가능성 분석', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7f42-bbb3-0d7232aaafab', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '비용 효익 분석', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 첫 번째 프로젝트 - 설계 단계 체크리스트 (20개)
INSERT INTO project_check_list (id, project_step_id, title, approval, created_at, deleted)
VALUES
-- PENDING (11개)
(UNHEX(REPLACE('019763fb-3359-71b7-947d-b0fee678b560', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '시스템 아키텍처 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-799b-9b9f-b0e104455164', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '데이터베이스 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-74ad-aa84-687e110d3133', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), 'API 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-71e1-b719-6e4b675611f8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '화면 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-73b9-9285-e908f6deb434', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '컴포넌트 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-785c-9f1d-5299dbedb2b0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '보안 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7092-9b26-1764018380b9', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '성능 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-72d2-a4b9-fe2274205577', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '인터페이스 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7175-bf17-5063c8a2f769', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '배치 프로세스 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7ebc-b96a-58058d8b4c02', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '에러 처리 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-79af-877d-fd6ce1ecbba1', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '로깅 및 모니터링 설계', 'PENDING', CURRENT_TIMESTAMP(6), 0),

-- APPROVED (3개)
(UNHEX(REPLACE('019763fb-3359-77da-affc-bb365edcf361', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '설계 문서 작성', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-74b1-8ca1-480e15fb0405', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '설계 검토 및 승인', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7677-a30e-6da0a2f02efc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '프로토타입 설계', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

-- REJECTED (3개)
(UNHEX(REPLACE('019763fb-3359-75f4-977d-3186a89176e2', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '테스트 케이스 설계', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-74bd-8425-4edd912f0e8e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '배포 아키텍처 설계', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7762-bd00-7b4147d112fd', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '장애 복구 설계', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

-- UPDATE_REQUEST (3개)
(UNHEX(REPLACE('019763fb-3359-7e0b-9d4c-e4fde9379096', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '코딩 표준 정의', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7452-b996-6abe03441723', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '형상 관리 계획', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
(UNHEX(REPLACE('019763fb-3359-7433-b9d3-d452b29d2a4c', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '품질 검증 계획', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 2. 프로젝트 체크리스트 테스트 데이터
INSERT INTO project_check_list(id, project_step_id, title, approval, deleted, created_at)
VALUES
    -- 기획 단계 (project_step_id: 01975dbf-6fbe-7788-90b2-e57cb2b3da86)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7fc7-8805-c8992e8dd072'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 목표 및 범위 정의', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-74be-98ed-d2abb88a473a'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '이해관계자 식별 및 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7854-a8a9-12c0df2e8bc4'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '사용자 요구사항 수집', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-70a4-98b7-c9573b65763d'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '비즈니스 프로세스 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7b6e-a4ed-49e28f2967d5'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 일정 수립', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-765a-9828-0058c7abd192'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '예산 계획 수립', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7761-9c88-a959dc871f76'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '리스크 식별 및 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ca5-8dbf-1c6b9790b3c2'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '기술 검토 및 평가', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7448-a957-335d1e1bbfcf'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '팀 구성 및 역할 정의', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-73ec-bc2b-4c3307537cda'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '커뮤니케이션 계획 수립', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7020-9edc-89ed6fdf2ed1'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '품질 관리 계획 수립', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-7b12-a1e9-de49290f364f'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 헌장 작성', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-735c-912a-f68a0725d059'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '승인 프로세스 정의', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e67-a92d-be21356643a9'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '성공 기준 정의', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-7c4c-b3ad-8dab2ba13b18'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '경쟁사 분석', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-72f1-8bbb-9e990cf8730f'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '시장 조사', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7c6c-a8ff-cfea3f8140ff'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '특허 조사', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-7843-ba8e-5670ea8b9ea1'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '법적 요구사항 검토', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7c6f-a4e3-bce17c04eea6'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '규제 준수 사항 확인', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e77-b235-4bbce4a87807'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '보안 요구사항 정의', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),

    -- 분석 단계 (project_step_id: 01975dbf-6fbe-76f7-bf20-5aef5b2402a6)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7098-9177-b98abf776704'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '현재 시스템 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7dbf-b765-55988736ef81'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '데이터 흐름 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-76ba-8bc5-fc970b0335c6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '사용자 시나리오 작성', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ad9-bc73-d36467007487'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '성능 요구사항 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-77c9-845d-ccc18c81b1c6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '인터페이스 요구사항 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7311-aefc-18bb9ef9d0fd'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '통합 요구사항 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7727-a954-d57a23c28576'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '보안 취약점 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-79a8-b856-5279c62af5b5'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '용량 계획 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ca2-b088-2d78186bfa63'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '비용 효율성 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ebc-bad0-3ee2731f7563'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '운영 환경 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7195-bb00-64f747401862'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '유지보수 요구사항 분석', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-7876-b31f-484fdc9f3394'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '기능 요구사항 명세서 작성', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-791e-8dc0-7b20bcffacd2'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '비기능 요구사항 명세서 작성', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7253-86da-aa7879b7f119'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '사용자 스토리 작성', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-72e2-85f4-73bd14cc74e6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '레거시 시스템 호환성 분석', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f23-9c84-a4a5498504cc'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '타사 솔루션 비교 분석', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ec7-b494-33aea0b79f9c'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '클라우드 마이그레이션 분석', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-741a-9e42-a2aef267aaec'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '데이터베이스 설계 요구사항', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d23-9a35-a7b9b2675d83'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), 'API 설계 요구사항', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7836-b453-c9bf16345cce'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), 'UI/UX 요구사항 분석', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),

    -- 설계 단계 (project_step_id: 01975dbf-6fbe-7607-ae5a-86cceb89afc7)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7080-8f1c-109133705767'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '시스템 아키텍처 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-76d3-90a4-a97709797009'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '데이터베이스 스키마 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e19-870d-871d128e7df8'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), 'API 인터페이스 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f67-9f7a-7f4b7228e2fa'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '사용자 인터페이스 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7104-a632-02b3f838417c'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '보안 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d1a-b710-91fc3cb43688'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '성능 최적화 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7b46-891d-8108db080e08'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '에러 처리 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f18-90e1-4b8c03473645'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '로깅 및 모니터링 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7fae-99ea-6be955405786'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '배포 전략 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7936-8439-731643089249'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '백업 및 복구 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-754a-ae35-bd9f389c8a0d'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '확장성 설계', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-79f3-9b5d-30278048f67e'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '기술 스택 선정', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7a96-94ad-038d695adaf1'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '개발 환경 구성', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7788-b452-ef50acfc116e'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '코딩 표준 정의', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-75b0-ba37-8f5913996757'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '마이크로서비스 분해 설계', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7166-8001-5584aec815fe'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '컨테이너화 설계', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d7d-8e58-9f695bef02d8'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '서비스 메시 설계', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-717b-8da7-df151e2d8896'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '테스트 설계', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e52-978f-e927920d6034'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '성능 테스트 설계', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ade-8aaa-7db2d0499035'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '통합 테스트 설계', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6));
