# 🎄 우아한테크코스 프리코스 4주차 : 크리스마스 프로모션

## 목차

- [1. 구현 기능 목록](#ballotboxwithcheck-구현-기능-목록)
- [2. 문제 해결 과정](#bookmark_tabs-문제-해결-과정)
   - [2.1 🧪 3주 차 미션 피드백과 적용 사항](#-3주-차-미션-피드백과-적용-사항)
- [3. 다음 미션에 적용할 학습 내용](#ledger-다음-미션에-적용할-학습-내용)

# :ballot_box_with_check: 구현 기능 목록

- [ ] controller Package
   - [ ] EventController
      - view Package와 domain Package의 데이터를 전달하며 값을 출력하는 클래스
      - 프로그램을 실행하는 `run` 메서드


- [ ] domain Package
   - [ ] DateManager
      - 날짜와 관련된 할인을 적용하는 클래스
      - 새로운 인스턴스를 생성하고 반환하는 create 메서드
   - [ ] MenuManager
      - 메뉴와 메뉴의 개수, 메뉴의 가격을 Enum으로 관리하는 클래스
      - 새로운 인스턴스를 생성하고 반환하는 create 메서드
   - wrapper Package
      - [ ] Date
         - 입력받은 날짜에 대한 유효성 검사와 반환을 담당하는 클래스
         - 새로운 인스턴스를 생성하고 반환하는 create 메서드
         - 날짜가 숫자로 변환이 가능한지 확인하는 `validateDateType` 메서드
         - 날짜의 범위가 1 ~ 31 사이의 숫자가 맞는지 확인하는 `validateDateRange` 메서드
         - 날짜를 반환하는 `getDate` 메서드
      - [ ] Menu
         - 입력받은 메뉴에 대한 유효성 검사와 반환을 담당하는 클래스
         - 새로운 인스턴스를 생성하고 반환하는 create 메서드
         - 메뉴판에 있는 메뉴인지 확인하는  `validateMenuType` 메서드
      - [ ] MenuQuantity
         - 입력받은 메뉴 개수에 대한 유효성 검사와 반환을 담당하는 클래스
         - 새로운 인스턴스를 생성하고 반환하는 create 메서드
         - 메뉴 개수가 숫자로 변환이 가능한지 확인하는 `validatQuantityType` 메서드
         - 메뉴의 개수가 1 이상 인지 확인하는 `validateQuantityRange` 메서드
      - [ ] OrderedMenu
         - 주문받은 메뉴를 Map 형태로 갖고있는 클래스
         - 새로운 인스턴스를 생성하고 반환하는 create 메서드
         - 입력값을 split을 통해 메뉴와 메뉴 개수로 나누고 주문 양식과 같은지 확인하는 `validateOrderFormat` 메서드
         - 중복되는 메뉴가 있는지 확인하는 `validateMenuDuplicate` 메서드
         - 메뉴를 반환하는 `getMenu` 메서드
         - 메뉴 개수를 반환하는 `getMenuQuantity` 메서드


- [ ] handler Package
   - [ ] InputHandler
      - view Package의 ConsoleInput의 확장성과 변경 용이성을 위한 인터페이스
      - `inputValue` 메서드의 추상화
   - [ ] OutputHandler
      - view Package의 ConsoleOutput의 확장성과 변경 용이성을 위한 인터페이스
      - `requestVisitDayMessage` 메서드의 추상화
      - `requestMenuMessage` 메서드의 추상화
      - `printBenefit` 메서드의 추상화
   - [ ] ErrorHandler
      - Enum 클래스를 활용하여 프로그램에서 발생하는 에러 코드들을 관리하는 클래스
      - ErrorMessage와 함께 `IllegalArgumentException` 예외를 발생시키는 `getException` 메서드
   - [ ] ConstantsHandler
      - 프로그램내의 상수들을 관리하는 클래스


- [ ] view Package
   - [ ] ConsoleInput
      - 입력을 담당하는 클래스
      - 모든 입력값을 받아오는 `inputValue` 메서드 구현
   - [ ] ConsoleOutput
      - 출력을 담당하는 클래스
      - 이벤트 플래너 소개와 예상 방문 날짜 입력을 요구하는 `requestVisitDayMessage` 메서드 구현
      - 주문할 메뉴와 개수를 요청하는 `requestMenuMessage` 메서드 구현
      - 식당에서 받은 이벤트 혜택을 보여주는 `printBenefit` 메서드 구현

---

# :bookmark_tabs: 문제 해결 과정

## 🧪 3주 차 미션 피드백과 적용 사항


---

# :ledger: 다음 미션에 적용할 학습 내용