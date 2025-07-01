--  액티비티 로그 데이터 추가
insert into activity_log (
                         id,
                         action_time,
                         action_type,
                         target_type,
                         target_id,
                         actor_id,
                         actor_name,
                         actor_company_name,
                         company_type
)
VALUES (UUID_TO_BIN('01979c5d-7ef9-7045-9c9f-d776c71c8baa'), NOW(), 'MODIFY', 'PROJECT', UUID_TO_BIN('01975d9a-b4a8-7ff4-a838-510bc0e3d42a'), UUID_TO_BIN('01975dad-57c0-7814-8bb2-cd1baa8fe327'), '박지안', '주식회사 데이터바이트', 'DEV'),
        (UUID_TO_BIN('01979c5d-7efa-7796-b990-2164ae726386'), NOW(), 'CREATE', 'PROJECT_STEP', UUID_TO_BIN('01975dbf-6fbf-70ca-b419-c94a615144db'), UUID_TO_BIN('01975db3-ed5b-70c4-b11a-d0fb9bfcfa38'), '장지우', '주식회사 게임앤조이', 'CLIENT');

--  로그 디테일 데이터 추가
insert into log_detail (id,
                       activity_log_id,
                       field_type,
                       old_value,
                       new_value)
values (UUID_TO_BIN('01979c5d-7ef9-7a6b-8a90-a3c24b921e03'), UUID_TO_BIN('01979c5d-7ef9-7045-9c9f-d776c71c8baa'), 'PROJECT_NAME', '변경전 제목', '실시간 채팅 고객지원 시스템 개발');