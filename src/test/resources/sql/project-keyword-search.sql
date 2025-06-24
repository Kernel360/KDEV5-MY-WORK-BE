--- 프로젝트 데이터
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted,project_amount)
VALUES
    (UNHEX(REPLACE('01975f5b-b0c0-796f-b15a-cbbeedabe1e3','-','')),
     '고객사 개발 프로젝트01', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다01', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-723f-b41c-bc922a25f86c','-','')),
     '고객사 개발 프로젝트02', NOW(), NOW(), 'IN_PROGRESS', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다02', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7478-8517-333d362cfc6a','-','')),
     '고객사 개발 프로젝트03', NOW(), NOW(), 'PAUSED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다03', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-73c8-99d8-c7ea5aaba8d5','-','')),
     '고객사 개발 프로젝트04', NOW(), NOW(), 'COMPLETED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7c72-92b8-a46e34d4413b','-','')),
     '고객사 개발 프로젝트05', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7b42-b3b1-b1b4194699a3','-','')),
     '고객사 개발 프로젝트06', NOW(), NOW(), 'IN_PROGRESS', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7abd-bf8c-5fe34b889870','-','')),
     '고객사 개발 프로젝트07', NOW(), NOW(), 'PAUSED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다05', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-73d4-a7b5-77de269e1a34','-','')),
     '고객사 개발 프로젝트08', NOW(), NOW(), 'COMPLETED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다08', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7010-a0de-8dc2756c99ee','-','')),
     '고객사 개발 프로젝트09', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다09', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7966-b08c-9b69ed36fa6a','-','')),
     '고객사 개발 프로젝트10', NOW(), NOW(), 'IN_PROGRESS', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다10', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7cb1-89da-1761c3b927e0','-','')),
     '고객사 개발 프로젝트11', NOW(), NOW(), 'PAUSED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다11', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7ff8-8efe-e04459d2c70b','-','')),
     '고객사 개발 프로젝트12', NOW(), NOW(), 'COMPLETED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다12', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7d83-88c3-30f058bdaf7a','-','')),
     '고객사 개발 프로젝트13', NOW(), NOW(), 'NOT_STARTED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다13', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-734b-8fca-f3f88410f6dc','-','')),
     '고객사 개발 프로젝트14', NOW(), NOW(), 'IN_PROGRESS', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다14', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-74ca-9891-cfe2bd4b4e16','-','')),
     '고객사 개발 프로젝트15', NOW(), NOW(), 'PAUSED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다15', 0,0),
    (UNHEX(REPLACE('01975f5b-b0c0-7d6a-af1f-9146009079ad','-','')),
     '고객사 개발 프로젝트16', NOW(), NOW(), 'COMPLETED', NOW(), NOW(), '고객사의 웹페이지를 구성해주는 프로젝트입니다16', 0,0);

--- 프로젝트 할당 데이터
INSERT INTO project_assign (id, project_id, dev_company_id, client_company_id, created_at)
VALUES
-- Project 01
(UNHEX(REPLACE('01975f65-a5f0-78cf-b4ad-2e7bdfa3c655','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-796f-b15a-cbbeedabe1e3','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7867-8224-57f8725c8221','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7130-8d02-e52ddc218c10','-','')),
 NOW()),

-- Project 02
(UNHEX(REPLACE('01975f65-a5f0-755c-b7e0-4ba37569d13d','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-723f-b41c-bc922a25f86c','-','')),
 UNHEX(REPLACE('01975f65-a5f0-78a2-8fe3-6e4f59219b28','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7a15-9015-84829e9c4cc7','-','')),
 NOW()),

-- Project 03
(UNHEX(REPLACE('01975f65-a5f0-7f4b-acc5-1d3f04b733b2','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7478-8517-333d362cfc6a','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7fc9-95af-95ef0b46b08e','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7c20-be94-c216eeb95e81','-','')),
 NOW()),

-- Project 04
(UNHEX(REPLACE('01975f65-a5f0-70b0-b6b0-c3fbe6a46243','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-73c8-99d8-c7ea5aaba8d5','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7d6f-936c-77dc44775b48','-','')),
 UNHEX(REPLACE('01975f65-a5f0-790f-a811-6c8a93a2aa3f','-','')),
 NOW()),

-- Project 05
(UNHEX(REPLACE('01975f65-a5f0-781f-9d91-3099f7055c69','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7c72-92b8-a46e34d4413b','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7886-aa3d-aa80a666ea96','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7941-b015-23b1e0c9ad68','-','')),
 NOW()),

-- Project 06
(UNHEX(REPLACE('01975f65-a5f0-7207-8df4-b150cf5b1ea9','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7b42-b3b1-b1b4194699a3','-','')),
 UNHEX(REPLACE('01975f65-a5f0-79ea-87ac-6bb74cd9f3c2','-','')),
 UNHEX(REPLACE('01975f65-a5f0-70bb-ac04-5e95c0a62b18','-','')),
 NOW()),

-- Project 07
(UNHEX(REPLACE('01975f65-a5f0-74ce-9c5b-2ec1e9a387cd','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7abd-bf8c-5fe34b889870','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7006-a1d6-707ef7444557','-','')),
 UNHEX(REPLACE('01975f65-a5f0-73c4-9b6d-d241237148c7','-','')),
 NOW()),

-- Project 08
(UNHEX(REPLACE('01975f65-a5f0-7f7a-82f6-f6e6f37b5c48','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-73d4-a7b5-77de269e1a34','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7bc2-9550-5600d57a03e6','-','')),
 UNHEX(REPLACE('01975f65-a5f0-744f-b65e-e22d9adbe4b7','-','')),
 NOW()),

-- Project 09
(UNHEX(REPLACE('01975f65-a5f0-78fc-a860-f55d62b82139','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7010-a0de-8dc2756c99ee','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7999-9b70-edb87a04b624','-','')),
 UNHEX(REPLACE('01975f65-a5f0-75b1-90be-63f0e3f9aeee','-','')),
 NOW()),

-- Project 10
(UNHEX(REPLACE('01975f65-a5f0-7029-a93e-5bace9a66b8c','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7966-b08c-9b69ed36fa6a','-','')),
 UNHEX(REPLACE('01975f65-a5f0-72ca-8c6d-c01bc65a0735','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7725-83ff-d0a8df9c8a26','-','')),
 NOW()),

-- Project 11
(UNHEX(REPLACE('01975f65-a5f0-7c75-90c5-4f52b965485e','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7cb1-89da-1761c3b927e0','-','')),
 UNHEX(REPLACE('01975f65-a5f0-731f-b063-cab64ddf3495','-','')),
 UNHEX(REPLACE('01975f65-a5f0-704c-addb-7eea48b2ea3a','-','')),
 NOW()),

-- Project 12
(UNHEX(REPLACE('01975f65-a5f0-7d99-8eb9-873aab007908','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7ff8-8efe-e04459d2c70b','-','')),
 UNHEX(REPLACE('01975f65-a5f0-7ea0-a4cb-2d4c34d2d415','-','')),
 UNHEX(REPLACE('01975f65-a5f0-75a8-ad2c-dc628109ca0e','-','')),
 NOW()),

-- Project 13
(UNHEX(REPLACE('01975f65-a5f0-7315-a9ea-08d3377078ad','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7d83-88c3-30f058bdaf7a','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7cef-a8f3-db0fd1edcbc5','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7d3e-9570-429e704af679','-','')),
 NOW()),

-- Project 14
(UNHEX(REPLACE('01975f65-a5f1-7fb1-9678-16cf4a3386f6','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-734b-8fca-f3f88410f6dc','-','')),
 UNHEX(REPLACE('01975f65-a5f1-71af-b34e-62909ca87ee9','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7771-93b5-e25bd1398c8e','-','')),
 NOW()),

-- Project 15
(UNHEX(REPLACE('01975f65-a5f1-7494-a1f7-35e07d959572','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-74ca-9891-cfe2bd4b4e16','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7e95-b146-a2561687f3d8','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7627-a00f-047734d757dc','-','')),
 NOW()),

-- Project 16
(UNHEX(REPLACE('01975f65-a5f1-74cd-8253-35be3008e955','-','')),
 UNHEX(REPLACE('01975f5b-b0c0-7d6a-af1f-9146009079ad','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7af7-9dcc-3e3d2a048cf6','-','')),
 UNHEX(REPLACE('01975f65-a5f1-7cc8-ba92-ec08834f084b','-','')),
 NOW());

--- 개발사 데이터
INSERT INTO company (id, name, contact_phone_number, business_number, address, contact_email, detail, file_name, type, deleted, created_at, modified_at)
VALUES
-- 개발사01
(UNHEX(REPLACE('01975f65-a5f0-7867-8224-57f8725c8221','-','')),
 '개발사 이름01', '02-1234-5601', '123-45-67801', '서울특별시 강남구 테헤란로 123, 1층', 'contact01@devcompany.com', '웹 개발 전문 개발사입니다01', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사02
(UNHEX(REPLACE('01975f65-a5f0-78a2-8fe3-6e4f59219b28','-','')),
 '개발사 이름02', '02-1234-5602', '123-45-67802', '서울특별시 강남구 테헤란로 124, 2층', 'contact02@devcompany.com', '웹 개발 전문 개발사입니다02', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사03
(UNHEX(REPLACE('01975f65-a5f0-7fc9-95af-95ef0b46b08e','-','')),
 '개발사 이름03', '02-1234-5603', '123-45-67803', '서울특별시 강남구 테헤란로 125, 3층', 'contact03@devcompany.com', '웹 개발 전문 개발사입니다03', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사04
(UNHEX(REPLACE('01975f65-a5f0-7d6f-936c-77dc44775b48','-','')),
 '개발사 이름04', '02-1234-5604', '123-45-67804', '서울특별시 강남구 테헤란로 126, 4층', 'contact04@devcompany.com', '웹 개발 전문 개발사입니다04', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사05
(UNHEX(REPLACE('01975f65-a5f0-7886-aa3d-aa80a666ea96','-','')),
 '개발사 이름05', '02-1234-5605', '123-45-67805', '서울특별시 강남구 테헤란로 127, 5층', 'contact05@devcompany.com', '웹 개발 전문 개발사입니다05', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사06
(UNHEX(REPLACE('01975f65-a5f0-79ea-87ac-6bb74cd9f3c2','-','')),
 '개발사 이름06', '02-1234-5606', '123-45-67806', '서울특별시 강남구 테헤란로 128, 6층', 'contact06@devcompany.com', '웹 개발 전문 개발사입니다06', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사07
(UNHEX(REPLACE('01975f65-a5f0-7006-a1d6-707ef7444557','-','')),
 '개발사 이름07', '02-1234-5607', '123-45-67807', '서울특별시 강남구 테헤란로 129, 7층', 'contact07@devcompany.com', '웹 개발 전문 개발사입니다07', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사08
(UNHEX(REPLACE('01975f65-a5f0-7bc2-9550-5600d57a03e6','-','')),
 '개발사 이름08', '02-1234-5608', '123-45-67808', '서울특별시 강남구 테헤란로 130, 8층', 'contact08@devcompany.com', '웹 개발 전문 개발사입니다08', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사09
(UNHEX(REPLACE('01975f65-a5f0-7999-9b70-edb87a04b624','-','')),
 '개발사 이름09', '02-1234-5609', '123-45-67809', '서울특별시 강남구 테헤란로 131, 9층', 'contact09@devcompany.com', '웹 개발 전문 개발사입니다09', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사10
(UNHEX(REPLACE('01975f65-a5f0-72ca-8c6d-c01bc65a0735','-','')),
 '개발사 이름10', '02-1234-5610', '123-45-67810', '서울특별시 강남구 테헤란로 132, 10층', 'contact10@devcompany.com', '웹 개발 전문 개발사입니다10', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사11
(UNHEX(REPLACE('01975f65-a5f0-731f-b063-cab64ddf3495','-','')),
 '개발사 이름11', '02-1234-5611', '123-45-67811', '서울특별시 강남구 테헤란로 133, 11층', 'contact11@devcompany.com', '웹 개발 전문 개발사입니다11', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사12
(UNHEX(REPLACE('01975f65-a5f0-7ea0-a4cb-2d4c34d2d415','-','')),
 '개발사 이름12', '02-1234-5612', '123-45-67812', '서울특별시 강남구 테헤란로 134, 12층', 'contact12@devcompany.com', '웹 개발 전문 개발사입니다12', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사13
(UNHEX(REPLACE('01975f65-a5f1-7cef-a8f3-db0fd1edcbc5','-','')),
 '개발사 이름13', '02-1234-5613', '123-45-67813', '서울특별시 강남구 테헤란로 135, 13층', 'contact13@devcompany.com', '웹 개발 전문 개발사입니다13', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사14
(UNHEX(REPLACE('01975f65-a5f1-71af-b34e-62909ca87ee9','-','')),
 '개발사 이름14', '02-1234-5614', '123-45-67814', '서울특별시 강남구 테헤란로 136, 14층', 'contact14@devcompany.com', '웹 개발 전문 개발사입니다14', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사15
(UNHEX(REPLACE('01975f65-a5f1-7e95-b146-a2561687f3d8','-','')),
 '개발사 이름15', '02-1234-5615', '123-45-67815', '서울특별시 강남구 테헤란로 137, 15층', 'contact15@devcompany.com', '웹 개발 전문 개발사입니다15', NULL, 'DEV', 0, NOW(), NOW()),

-- 개발사16
(UNHEX(REPLACE('01975f65-a5f1-7af7-9dcc-3e3d2a048cf6','-','')),
 '개발사 이름16', '02-1234-5616', '123-45-67816', '서울특별시 강남구 테헤란로 138, 16층', 'contact16@devcompany.com', '웹 개발 전문 개발사입니다16', NULL, 'DEV', 0, NOW(), NOW());

--- 고객사 데이터
INSERT INTO company (id, name, contact_phone_number, business_number, address, contact_email, detail, file_name, type, deleted, created_at, modified_at)
VALUES
-- 고객사01
(UNHEX(REPLACE('01975f65-a5f0-7130-8d02-e52ddc218c10','-','')),
 '고객사 이름01', '02-2345-6701', '234-56-78901', '서울특별시 종로구 세종대로 201, 1층', 'client01@clientcompany.com', '웹서비스 운영 고객사입니다01', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사02
(UNHEX(REPLACE('01975f65-a5f0-7a15-9015-84829e9c4cc7','-','')),
 '고객사 이름02', '02-2345-6702', '234-56-78902', '서울특별시 종로구 세종대로 202, 2층', 'client02@clientcompany.com', '웹서비스 운영 고객사입니다02', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사03
(UNHEX(REPLACE('01975f65-a5f0-7c20-be94-c216eeb95e81','-','')),
 '고객사 이름03', '02-2345-6703', '234-56-78903', '서울특별시 종로구 세종대로 203, 3층', 'client03@clientcompany.com', '웹서비스 운영 고객사입니다03', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사04
(UNHEX(REPLACE('01975f65-a5f0-790f-a811-6c8a93a2aa3f','-','')),
 '고객사 이름04', '02-2345-6704', '234-56-78904', '서울특별시 종로구 세종대로 204, 4층', 'client04@clientcompany.com', '웹서비스 운영 고객사입니다04', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사05
(UNHEX(REPLACE('01975f65-a5f0-7941-b015-23b1e0c9ad68','-','')),
 '고객사 이름05', '02-2345-6705', '234-56-78905', '서울특별시 종로구 세종대로 205, 5층', 'client05@clientcompany.com', '웹서비스 운영 고객사입니다05', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사06
(UNHEX(REPLACE('01975f65-a5f0-70bb-ac04-5e95c0a62b18','-','')),
 '고객사 이름06', '02-2345-6706', '234-56-78906', '서울특별시 종로구 세종대로 206, 6층', 'client06@clientcompany.com', '웹서비스 운영 고객사입니다06', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사07
(UNHEX(REPLACE('01975f65-a5f0-73c4-9b6d-d241237148c7','-','')),
 '고객사 이름07', '02-2345-6707', '234-56-78907', '서울특별시 종로구 세종대로 207, 7층', 'client07@clientcompany.com', '웹서비스 운영 고객사입니다07', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사08
(UNHEX(REPLACE('01975f65-a5f0-744f-b65e-e22d9adbe4b7','-','')),
 '고객사 이름08', '02-2345-6708', '234-56-78908', '서울특별시 종로구 세종대로 208, 8층', 'client08@clientcompany.com', '웹서비스 운영 고객사입니다08', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사09
(UNHEX(REPLACE('01975f65-a5f0-75b1-90be-63f0e3f9aeee','-','')),
 '고객사 이름09', '02-2345-6709', '234-56-78909', '서울특별시 종로구 세종대로 209, 9층', 'client09@clientcompany.com', '웹서비스 운영 고객사입니다09', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사10
(UNHEX(REPLACE('01975f65-a5f0-7725-83ff-d0a8df9c8a26','-','')),
 '고객사 이름10', '02-2345-6710', '234-56-78910', '서울특별시 종로구 세종대로 210, 10층', 'client10@clientcompany.com', '웹서비스 운영 고객사입니다10', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사11
(UNHEX(REPLACE('01975f65-a5f0-704c-addb-7eea48b2ea3a','-','')),
 '고객사 이름11', '02-2345-6711', '234-56-78911', '서울특별시 종로구 세종대로 211, 11층', 'client11@clientcompany.com', '웹서비스 운영 고객사입니다11', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사12
(UNHEX(REPLACE('01975f65-a5f0-75a8-ad2c-dc628109ca0e','-','')),
 '고객사 이름12', '02-2345-6712', '234-56-78912', '서울특별시 종로구 세종대로 212, 12층', 'client12@clientcompany.com', '웹서비스 운영 고객사입니다12', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사13
(UNHEX(REPLACE('01975f65-a5f1-7d3e-9570-429e704af679','-','')),
 '고객사 이름13', '02-2345-6713', '234-56-78913', '서울특별시 종로구 세종대로 213, 13층', 'client13@clientcompany.com', '웹서비스 운영 고객사입니다13', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사14
(UNHEX(REPLACE('01975f65-a5f1-7771-93b5-e25bd1398c8e','-','')),
 '고객사 이름14', '02-2345-6714', '234-56-78914', '서울특별시 종로구 세종대로 214, 14층', 'client14@clientcompany.com', '웹서비스 운영 고객사입니다14', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사15
(UNHEX(REPLACE('01975f65-a5f1-7627-a00f-047734d757dc','-','')),
 '고객사 이름15', '02-2345-6715', '234-56-78915', '서울특별시 종로구 세종대로 215, 15층', 'client15@clientcompany.com', '웹서비스 운영 고객사입니다15', NULL, 'CLIENT', 0, NOW(), NOW()),

-- 고객사16
(UNHEX(REPLACE('01975f65-a5f1-7cc8-ba92-ec08834f084b','-','')),
 '고객사 이름16', '02-2345-6716', '234-56-78916', '서울특별시 종로구 세종대로 216, 16층', 'client16@clientcompany.com', '웹서비스 운영 고객사입니다16', NULL, 'CLIENT', 0, NOW(), NOW());
