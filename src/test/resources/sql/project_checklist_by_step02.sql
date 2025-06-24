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
INSERT INTO project_check_list (id, project_step_id, title, content, approval, created_at, deleted)
VALUES
    -- PENDING (11개)
    (UNHEX(REPLACE('019763fb-3358-7781-9595-aacc94171030', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '사업 목표 정의', '본 결재 항목은 프로젝트의 전반적인 방향성과 목적을 정의하고 승인받기 위한 단계입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7334-8901-da109014f1bf', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '타겟 고객 분석', '대상 고객의 특성과 니즈를 분석한 자료를 기반으로 결재 요청을 진행합니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7684-9a02-66f825801a71', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '경쟁사 분석', '시장 내 경쟁사를 비교·분석한 리서치 결과에 대해 결재를 진행합니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7fae-bd63-8b135fc3cbe6', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 범위 설정', '프로젝트의 범위와 한계를 정의한 내용을 검토 및 결재받는 단계입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-773c-8ade-13f76d743bc5', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '기능 요구사항 정의', '기능적 요구사항 목록에 대한 정의서를 기반으로 결재를 요청합니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7d74-a48d-01372aa92811', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '비기능 요구사항 정의', '보안, 성능, 확장성 등 비기능 요구사항 문서에 대한 결재입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7836-80cf-1c0f6168dc56', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '사용자 스토리 작성', '사용자 입장에서 기능을 정의한 스토리 문서를 결재 요청합니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7266-b625-27fca2246f7e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 일정 계획', '프로젝트 전체 일정을 계획한 문서에 대한 결재입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-74a2-a46b-283af07b8ca0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '리소스 계획 수립', '인력, 장비 등 프로젝트에 필요한 자원의 배분 계획에 대한 결재입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7d04-8a2b-6c29f62d5b82', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '위험 요소 식별', '프로젝트 리스크를 사전에 식별하고 대응방안을 마련한 문서의 결재입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-78e3-a87d-7afdb07a79c8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '예산 계획 수립', '예산 항목 및 소요 비용 계획을 정리한 문서를 결재 요청합니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),

    (UNHEX(REPLACE('019763fb-3358-7ad4-b7fc-119490c04d49', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '프로젝트 팀 구성', '프로젝트를 수행할 핵심 인력과 각자의 역할을 정의하고 구성한 팀 구성안을 기반으로 결재를 진행합니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-71bb-b0a0-bc8a792b62c8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '이해관계자 식별', '프로젝트에 직간접적으로 영향을 미칠 주요 이해관계자를 정의한 문서에 대한 결재입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7747-8b24-cecc70cb47b1', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '커뮤니케이션 계획', '프로젝트 내외부 커뮤니케이션 방식과 주체, 채널 등을 정리한 계획서의 결재입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

    -- REJECTED (3개)
    (UNHEX(REPLACE('019763fb-3358-7ef3-8642-92548754b18e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '기술 스택 선정', '선정된 기술 스택이 프로젝트의 요구사항이나 팀의 역량과 부합하지 않아 반려되었습니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-79dd-9912-f5340efc810f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '품질 기준 정의', '제시된 품질 기준이 불충분하거나 측정 가능한 지표가 명확하지 않아 반려되었습니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7cd4-8b92-95a2c51d2014', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '성공 기준 정의', '성공 기준이 모호하거나 측정 가능성이 부족해 해당 항목이 반려되었습니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

    -- UPDATE_REQUEST (3개)
    (UNHEX(REPLACE('019763fb-3358-7374-bc16-8b462972a6a2', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '승인 프로세스 정의', '결재 단계의 명확성이나 책임자의 지정이 부족하여 수정을 요청드립니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7e91-990d-bf5650cbf45d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '변경 관리 프로세스', '변경 요청 처리 흐름과 기준에 대한 설명이 부족하여 업데이트가 필요합니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-72ea-b5f9-10b55ea29044', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7e91-bb1d-9acdfe6ed3d5', '-', '')), '문서화 표준 정의', '문서 형식, 작성 기준 등 구체적인 표준 제시가 부족하여 수정을 요청드립니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 첫 번째 프로젝트 - 분석 단계 체크리스트 (20개)
INSERT INTO project_check_list (id, project_step_id, title, content, approval, created_at, deleted)
VALUES
    -- PENDING (11개)
    (UNHEX(REPLACE('019763fb-3358-7b65-90d1-f7a900380612', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '현재 시스템 분석', '기존 시스템의 기능, 구조, 성능 및 문제점을 분석하여 개선 방향을 도출하기 위한 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3358-7ce6-a320-5bf673ea587d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '데이터 플로우 분석', '시스템 내 데이터 흐름을 시각화하고 주요 입력, 출력, 저장 및 처리 과정을 파악하기 위한 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7ed2-9933-8cac4e213278', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '사용자 행동 패턴 분석', '사용자의 시스템 이용 방식과 반복 행태를 파악하여 UX 개선과 요구사항 정의에 반영하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7f71-bdd3-399e3f60790c', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '성능 요구사항 분석', '시스템이 충족해야 할 응답 시간, 처리량 등 성능 목표를 정의하고 분석하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7e96-94cc-17a4533815c0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '보안 요구사항 분석', '데이터 보호, 접근 제어, 인증 등 시스템 보안을 위한 요구사항을 식별하고 분석하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7e0a-8d74-94e0685c9072', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '통합 요구사항 분석', '외부 시스템 또는 모듈 간 연동 및 데이터 교환을 위한 요구사항을 정의하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7880-ab6b-a3ea26fd5c96', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), 'UI/UX 요구사항 분석', '사용자 인터페이스와 사용자 경험 향상을 위한 레이아웃, 동선, 디자인 요소 등을 분석하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7928-a68b-8b02e379a8cc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '데이터 요구사항 분석', '저장, 처리, 보존되어야 할 데이터 항목과 구조를 명확히 하기 위한 분석 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7fb2-a5b0-8d487ed33d73', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '인터페이스 요구사항 분석', '시스템과 외부 모듈 간 연결 및 사용자와의 상호작용을 위한 인터페이스 요건을 분석하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-74df-be4a-3c651512fa71', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '비즈니스 프로세스 분석', '업무 흐름 및 운영 절차를 분석하여 시스템의 기능적 요구사항 도출에 활용하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-78e9-a260-d646fb8e798f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '기술적 제약사항 분석', '기술 스택, 인프라 환경, 라이선스 등의 제약 조건을 식별하고 반영하기 위한 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),

    -- APPROVED (3개)
    (UNHEX(REPLACE('019763fb-3359-7d50-87c6-76bffa89af73', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 우선순위 설정', '요구사항 간의 중요도와 긴급도를 판단하여 구현 순서를 결정하고 자원 배분의 기준을 마련하기 위한 항목입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-77e7-b9d3-96569f6850fc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '이해관계자 요구사항 검증', '수집된 요구사항이 실제 이해관계자의 기대를 충족하는지 확인하고 조율하는 항목입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7543-973f-4bb4db2a3d3f', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 추적성 매트릭스', '요구사항이 전체 프로젝트 흐름과 어떤 연관 관계를 가지는지 명확하게 정리하는 문서화 작업 항목입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

    -- REJECTED (3개)
    (UNHEX(REPLACE('019763fb-3359-7d01-bf1e-ae511c4977de', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '레거시 시스템 영향 분석', '기존 시스템의 구조와 기능이 새로운 시스템에 미치는 영향과 제약을 분석하는 항목입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-700a-9d68-e7b38a7e969e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '마이그레이션 전략 분석', '기존 시스템에서 새로운 환경으로 전환 시 필요한 전략과 절차를 수립하는 항목입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-794b-8efa-187ff6b49d5d', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '운영 영향도 분석', '새로운 시스템 도입으로 인한 기존 운영 업무의 변화와 영향을 분석하는 항목입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

    -- UPDATE_REQUEST (3개)
    (UNHEX(REPLACE('019763fb-3359-7e0b-a5e0-eca1da1bac57', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '요구사항 변경 영향도 분석', '요구사항 변경이 시스템 전체에 미치는 영향을 사전에 예측하고 조율하기 위한 항목입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-70d7-a72b-bc65b1ba1e34', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '기술적 실현가능성 분석', '요구된 기능이나 성능을 현재 기술로 실제 구현할 수 있는지를 분석하는 항목입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7f42-bbb3-0d7232aaafab', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-7ef6-b8b4-d57778655aea', '-', '')), '비용 효익 분석', '투입되는 비용 대비 기대되는 이익을 평가하여 요구사항의 타당성을 판단하는 항목입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 첫 번째 프로젝트 - 설계 단계 체크리스트 (20개)
INSERT INTO project_check_list (id, project_step_id, title, content, approval, created_at, deleted)
VALUES
    -- PENDING (11개)
    (UNHEX(REPLACE('019763fb-3359-71b7-947d-b0fee678b560', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '시스템 아키텍처 설계', '시스템 전반의 구조를 정의하고 주요 구성 요소 간의 상호작용 및 책임 분리를 명확히 하기 위한 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-799b-9b9f-b0e104455164', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '데이터베이스 설계', '시스템에 필요한 데이터 구조와 관계를 정의하여 일관성과 무결성을 보장하기 위한 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-74ad-aa84-687e110d3133', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), 'API 설계', '시스템 간 데이터 교환을 위한 엔드포인트 정의 및 명세 작성을 포함하는 인터페이스 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-71e1-b719-6e4b675611f8', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '화면 설계', '사용자 인터페이스의 레이아웃과 구성 요소 배치를 설계하여 사용자 경험을 최적화하기 위한 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-73b9-9285-e908f6deb434', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '컴포넌트 설계', '재사용 가능한 UI 또는 로직 단위를 정의하고 역할과 데이터 흐름을 명확히 하기 위한 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-785c-9f1d-5299dbedb2b0', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '보안 설계', '시스템의 취약점을 사전에 예방하고 데이터 보호를 위한 인증, 권한, 암호화 등의 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7092-9b26-1764018380b9', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '성능 설계', '시스템의 응답 시간과 처리량을 고려하여 병목 현상을 줄이고 효율적인 처리를 위한 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-72d2-a4b9-fe2274205577', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '인터페이스 설계', '시스템 내부 또는 외부 모듈과의 데이터 흐름과 포맷을 정의하여 원활한 통합을 위한 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7175-bf17-5063c8a2f769', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '배치 프로세스 설계', '정기적으로 수행되는 대량 데이터 작업의 흐름과 트리거를 정의하기 위한 설계 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7ebc-b96a-58058d8b4c02', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '에러 처리 설계', '시스템 오류 발생 시 사용자와 개발자가 빠르게 원인을 파악할 수 있도록 처리 흐름을 설계하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-79af-877d-fd6ce1ecbba1', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '로깅 및 모니터링 설계', '시스템 운영 중 발생하는 이벤트 및 성능 정보를 기록하고 분석할 수 있도록 설계하는 항목입니다.', 'PENDING', CURRENT_TIMESTAMP(6), 0),

    -- APPROVED (3개)
    (UNHEX(REPLACE('019763fb-3359-77da-affc-bb365edcf361', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '설계 문서 작성', '시스템 구조, 데이터 흐름, 모듈 간 인터페이스 등을 문서화하여 개발 및 유지보수 시 참조 가능한 자료를 생성하는 작업입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-74b1-8ca1-480e15fb0405', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '설계 검토 및 승인', '작성된 설계 문서를 이해관계자들과 검토하고 피드백을 반영하여 공식적으로 승인받기 위한 절차입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7677-a30e-6da0a2f02efc', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '프로토타입 설계', '핵심 기능 또는 UI/UX를 미리 구현하여 설계 아이디어를 시각화하고 검증하기 위한 작업입니다.', 'APPROVED', CURRENT_TIMESTAMP(6), 0),

    -- REJECTED (3개)
    (UNHEX(REPLACE('019763fb-3359-75f4-977d-3186a89176e2', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '테스트 케이스 설계', '요구사항을 기반으로 기능별 테스트 항목과 기대 결과를 정의하여 품질을 보장하기 위한 준비 작업입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-74bd-8425-4edd912f0e8e', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '배포 아키텍처 설계', '애플리케이션의 실행 환경, 배포 구조, 네트워크 구성 등을 정의하여 안정적인 운영을 위한 설계 항목입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7762-bd00-7b4147d112fd', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '장애 복구 설계', '시스템 장애 발생 시 빠르게 복구할 수 있도록 백업, 이중화, 대응 시나리오 등을 설계하는 항목입니다.', 'REJECTED', CURRENT_TIMESTAMP(6), 0),

    -- UPDATE_REQUEST (3개)
    (UNHEX(REPLACE('019763fb-3359-7e0b-9d4c-e4fde9379096', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '코딩 표준 정의', '일관된 코드 작성 방식을 유지하기 위해 네이밍 규칙, 들여쓰기, 주석 처리 등의 기준을 정의하는 작업입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7452-b996-6abe03441723', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '형상 관리 계획', '소스코드 버전 관리, 브랜치 전략, 변경 이력 관리를 통해 협업과 품질 유지에 필요한 체계를 구축하는 항목입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0),
    (UNHEX(REPLACE('019763fb-3359-7433-b9d3-d452b29d2a4c', '-', '')), UNHEX(REPLACE('01975dbf-6fbe-71b9-8dab-0455e155bbe8', '-', '')), '품질 검증 계획', '개발 산출물이 요구사항을 충족하는지 확인하기 위한 검증 절차와 기준을 문서화하는 항목입니다.', 'UPDATE_REQUEST', CURRENT_TIMESTAMP(6), 0);

-- 2. 프로젝트 체크리스트 테스트 데이터
INSERT INTO project_check_list(id, project_step_id, title, content, approval, deleted, created_at)
VALUES
    -- 기획 단계 (project_step_id: 01975dbf-6fbe-7788-90b2-e57cb2b3da86)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7fc7-8805-c8992e8dd072'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 목표 및 범위 정의', '프로젝트의 목적과 달성해야 할 결과물, 작업 범위를 명확히 설정하여 전체적인 방향성과 기대치를 정의합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-74be-98ed-d2abb88a473a'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '이해관계자 식별 및 분석', '프로젝트에 영향을 주거나 영향을 받는 모든 이해관계자를 식별하고 그들의 기대사항과 우선순위를 분석합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7854-a8a9-12c0df2e8bc4'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '사용자 요구사항 수집', '최종 사용자 및 관련자의 의견을 통해 시스템이 충족해야 할 기능적, 비기능적 요구사항을 도출합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-70a4-98b7-c9573b65763d'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '비즈니스 프로세스 분석', '현재의 비즈니스 흐름을 이해하고 시스템이 어떻게 이를 지원할 수 있을지 분석하여 개선점을 도출합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7b6e-a4ed-49e28f2967d5'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 일정 수립', '프로젝트의 주요 활동들을 정의하고 각 활동의 순서 및 기간을 설정하여 전체 일정 계획을 수립합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-765a-9828-0058c7abd192'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '예산 계획 수립', '프로젝트 수행에 필요한 인력, 장비, 자원 등에 대한 비용을 예측하고 예산을 할당합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7761-9c88-a959dc871f76'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '리스크 식별 및 분석', '프로젝트 진행 중 발생할 수 있는 위험 요소를 사전에 파악하고, 그 영향도 및 대응 전략을 정의합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ca5-8dbf-1c6b9790b3c2'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '기술 검토 및 평가', '도입 가능한 기술 옵션을 조사하고, 프로젝트 요구사항에 가장 적합한 기술 스택을 선정하기 위한 평가를 수행합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7448-a957-335d1e1bbfcf'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '팀 구성 및 역할 정의', '프로젝트 팀의 역할과 책임을 정의하고 적절한 인력을 배정하여 효율적인 협업 환경을 조성합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-73ec-bc2b-4c3307537cda'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '커뮤니케이션 계획 수립', '이해관계자 간 원활한 정보 공유를 위한 커뮤니케이션 방식, 주기, 채널 등을 정의합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7020-9edc-89ed6fdf2ed1'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '품질 관리 계획 수립', '산출물의 품질을 유지하기 위한 품질 기준과 검토 절차, 검증 방식 등을 문서화하여 체계화합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-7b12-a1e9-de49290f364f'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '프로젝트 헌장 작성', '프로젝트의 목적, 범위, 목표 등을 공식적으로 문서화하여 프로젝트 시작의 기준을 제공합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-735c-912a-f68a0725d059'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '승인 프로세스 정의', '산출물 및 주요 결정 사항에 대한 승인 절차와 책임자를 정의하여 프로젝트의 통제 체계를 확립합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e67-a92d-be21356643a9'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '성공 기준 정의', '프로젝트 성공을 판단할 수 있는 정량적·정성적 기준을 설정하여 목표 달성 여부를 평가할 수 있도록 합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-7c4c-b3ad-8dab2ba13b18'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '경쟁사 분석', '시장 내 경쟁사들의 제품, 전략, 강약점을 분석하여 우리 프로젝트의 차별화 요소를 도출합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-72f1-8bbb-9e990cf8730f'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '시장 조사', '시장 동향, 수요, 소비자 요구를 조사하여 프로젝트 기획 및 방향성 수립에 반영합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7c6c-a8ff-cfea3f8140ff'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '특허 조사', '유사 서비스나 기술에 대한 특허 현황을 조사하여 법적 리스크를 사전에 차단합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-7843-ba8e-5670ea8b9ea1'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '법적 요구사항 검토', '프로젝트와 관련된 법률 및 규제를 검토하여 준수 사항을 명확히 하고 리스크를 최소화합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7c6f-a4e3-bce17c04eea6'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '규제 준수 사항 확인', '산업별 규제 및 표준을 검토하고, 프로젝트가 이에 부합하는지 여부를 점검합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e77-b235-4bbce4a87807'), UNHEX(REPLACE('01975dbf-6fbe-7788-90b2-e57cb2b3da86','-','')), '보안 요구사항 정의', '데이터 보호와 시스템 접근 통제를 위한 보안 요구사항을 정의하여 안정성을 확보합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),

    -- 분석 단계 (project_step_id: 01975dbf-6fbe-76f7-bf20-5aef5b2402a6)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7098-9177-b98abf776704'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '현재 시스템 분석', '기존 시스템의 구조, 기능, 성능 등을 분석하여 개선 또는 연동 방향을 도출합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7dbf-b765-55988736ef81'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '데이터 흐름 분석', '시스템 내 데이터가 생성, 이동, 저장되는 경로를 분석하여 설계 기반을 마련합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-76ba-8bc5-fc970b0335c6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '사용자 시나리오 작성', '실제 사용자의 행동을 시나리오 형식으로 정리하여 UX 향상 및 기능 정의에 활용합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ad9-bc73-d36467007487'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '성능 요구사항 분석', '시스템이 처리해야 하는 트래픽, 응답 시간, 처리 속도 등 성능 기준을 정의합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-77c9-845d-ccc18c81b1c6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '인터페이스 요구사항 분석', '내외부 시스템 간의 연계 방식 및 데이터 포맷 등 인터페이스 관련 요구사항을 정리합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7311-aefc-18bb9ef9d0fd'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '통합 요구사항 분석', '각 시스템 간의 통합 방식, 타이밍, 데이터 정합성 등 통합 운영에 필요한 요구사항을 도출합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7727-a954-d57a23c28576'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '보안 취약점 분석', '시스템에서 발생할 수 있는 보안 위협 요소를 식별하고 대응 방안을 수립합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-79a8-b856-5279c62af5b5'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '용량 계획 분석', '시스템이 처리할 데이터의 양과 저장 공간 등을 고려한 용량 계획을 수립합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ca2-b088-2d78186bfa63'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '비용 효율성 분석', '예산 대비 효과적인 기술 및 자원 활용 방안을 분석하여 비용 최적화를 도모합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ebc-bad0-3ee2731f7563'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '운영 환경 분석', '시스템이 배포될 하드웨어, 네트워크, 소프트웨어 등 운영 인프라 환경을 분석합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7195-bb00-64f747401862'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '유지보수 요구사항 분석', '장기적인 운영과 개선을 고려하여 유지보수에 필요한 요구사항을 정의합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-7876-b31f-484fdc9f3394'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '기능 요구사항 명세서 작성', '시스템에서 제공해야 하는 주요 기능들을 문서화하여 개발 기준을 제공합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-791e-8dc0-7b20bcffacd2'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '비기능 요구사항 명세서 작성', '보안, 성능, 가용성 등 기능 외의 요구사항을 명확히 문서화하여 품질 기준을 수립합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7253-86da-aa7879b7f119'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '사용자 스토리 작성', '사용자 관점에서 기능 사용 시나리오를 서술하여 요구사항에 생생함과 명확성을 더합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-72e2-85f4-73bd14cc74e6'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '레거시 시스템 호환성 분석', '기존 시스템과의 연동 또는 마이그레이션 가능성을 평가하여 신뢰성 있는 전환을 목표로 합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f23-9c84-a4a5498504cc'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '타사 솔루션 비교 분석', '경쟁 제품 및 솔루션과 비교하여 자사 시스템의 우위 및 보완점을 분석합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ec7-b494-33aea0b79f9c'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '클라우드 마이그레이션 분석', '온프레미스 시스템을 클라우드 환경으로 이전 시 고려할 요소들을 분석합니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-741a-9e42-a2aef267aaec'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), '데이터베이스 설계 요구사항', '데이터 구조, 정규화, 인덱싱, 확장성 등을 포함한 DB 설계에 필요한 요구사항을 정의합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d23-9a35-a7b9b2675d83'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), 'API 설계 요구사항', '외부 시스템 또는 프론트와의 통신을 위한 API의 명세 및 설계 기준을 정의합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7836-b453-c9bf16345cce'), UNHEX(REPLACE('01975dbf-6fbe-76f7-bf20-5aef5b2402a6','-','')), 'UI/UX 요구사항 분석', '사용자의 편의성과 경험을 최우선으로 고려한 화면 구성과 인터랙션 요구사항을 정의합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),

-- 설계 단계 (project_step_id: 01975dbf-6fbe-7607-ae5a-86cceb89afc7)
    -- PENDING 11건
    (UUID_TO_BIN('01976429-a9ac-7080-8f1c-109133705767'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '시스템 아키텍처 설계', '전체 시스템의 구성 요소와 상호작용을 정의하여 안정적이고 확장 가능한 구조를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-76d3-90a4-a97709797009'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '데이터베이스 스키마 설계', '데이터의 효율적인 저장과 조회를 위한 테이블 구조와 관계를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e19-870d-871d128e7df8'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), 'API 인터페이스 설계', '내부 및 외부 시스템과의 통신을 위한 API 명세를 정의하고 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f67-9f7a-7f4b7228e2fa'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '사용자 인터페이스 설계', '직관적이고 일관된 사용자 경험을 위한 UI 구성과 흐름을 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7104-a632-02b3f838417c'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '보안 설계', '인증, 인가, 데이터 보호 등을 포함한 시스템 보안 정책과 구조를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d1a-b710-91fc3cb43688'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '성능 최적화 설계', '시스템 응답성과 처리 속도를 높이기 위한 구조 및 로직을 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7b46-891d-8108db080e08'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '에러 처리 설계', '예외 상황 발생 시 안정적인 시스템 유지를 위한 처리 로직을 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7f18-90e1-4b8c03473645'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '로깅 및 모니터링 설계', '시스템 상태 및 오류 추적을 위한 로그 및 모니터링 체계를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7fae-99ea-6be955405786'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '배포 전략 설계', '지속적 통합 및 배포를 고려한 효율적이고 안정적인 배포 전략을 수립합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7936-8439-731643089249'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '백업 및 복구 설계', '데이터 유실에 대비한 백업 체계와 복구 절차를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-754a-ae35-bd9f389c8a0d'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '확장성 설계', '미래의 사용자 증가나 기능 확장에 대비한 유연한 구조를 설계합니다.', 'PENDING', 0, CURRENT_TIMESTAMP(6)),

    -- APPROVED 3건
    (UUID_TO_BIN('01976429-a9ac-79f3-9b5d-30278048f67e'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '기술 스택 선정', '현 프로젝트에 가장 적합한 언어, 프레임워크, 데이터베이스 등의 기술 스택을 선정합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7a96-94ad-038d695adaf1'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '개발 환경 구성', '개발, 테스트, 운영 환경에서 일관된 결과를 낼 수 있도록 개발 환경을 표준화하고 구성합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7788-b452-ef50acfc116e'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '코딩 표준 정의', '코드의 일관성과 유지보수성을 높이기 위해 팀 내 공통 코딩 스타일과 규칙을 정의합니다.', 'APPROVED', 0, CURRENT_TIMESTAMP(6)),

    -- REJECTED 3건
    (UUID_TO_BIN('01976429-a9ac-75b0-ba37-8f5913996757'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '마이크로서비스 분해 설계', '모놀리식 구조를 여러 독립적인 서비스로 나누는 마이크로서비스 설계를 검토하고자 하였으나, 현재는 적용하지 않기로 결정되었습니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7166-8001-5584aec815fe'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '컨테이너화 설계', '애플리케이션을 컨테이너 기반으로 배포하기 위한 설계를 시도했지만, 환경 요건상 부적합하여 반려되었습니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7d7d-8e58-9f695bef02d8'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '서비스 메시 설계', '서비스 간 통신을 위한 메시 구성 방안을 검토했으나, 프로젝트 범위와 우선순위에 따라 적용이 보류되었습니다.', 'REJECTED', 0, CURRENT_TIMESTAMP(6)),

    -- UPDATE_REQUEST 3건
    (UUID_TO_BIN('01976429-a9ac-717b-8da7-df151e2d8896'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '테스트 설계', '시스템 안정성 확보를 위한 테스트 항목과 절차를 정의하고 있으며, 일부 항목은 보완이 필요합니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7e52-978f-e927920d6034'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '성능 테스트 설계', '시스템의 처리 능력과 응답 속도를 검증하기 위한 성능 테스트 계획을 수립 중입니다. 일부 지표 재정의 요청됨.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6)),
    (UUID_TO_BIN('01976429-a9ac-7ade-8aaa-7db2d0499035'), UNHEX(REPLACE('01975dbf-6fbe-7607-ae5a-86cceb89afc7','-','')), '통합 테스트 설계', '다양한 시스템 구성 요소의 통합 테스트 계획을 수립했으며, 인터페이스 검증 항목 추가 요청이 있습니다.', 'UPDATE_REQUEST', 0, CURRENT_TIMESTAMP(6));
