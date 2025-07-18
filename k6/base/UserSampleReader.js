// CSV 파일을 읽고 파싱하는 함수
export function loadMemberFromCSV() {
  const file = open('../../sample_data/member.csv');  // CSV 파일을 읽기
  const lines = file.split('\n');  // 각 줄을 구분자로 나누기
  const data = [];

  // 첫 번째 줄은 헤더이므로 건너뛰고 나머지 데이터 처리
  for (let i = 1; i < lines.length; i++) {
    const line = lines[i].trim();  // 빈 줄을 처리하기 위해 trim() 사용
    if (line) {
      const fields = line.split(',');  // 쉼표로 구분된 항목을 배열로 변환
      data.push({
        id: fields[0],
        email: fields[3],
        password: fields[8]
      });
    }
  }
  return data;  // 데이터를 반환
}
