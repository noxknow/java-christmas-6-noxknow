package christmas.domain;

public enum MenuManager {

    YANGSONGEE_SOUP("양송이수프", 6000, "에피타이저"),
    TAPAS("타파스", 5500, "에피타이저"),
    CAESAR_SALAD("시저샐러드", 8000, "에피타이저"),
    T_BONE_STEAK("티본스테이크", 55000, "메인"),
    BBQ_RIB("바비큐립", 54000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, "메인"),
    CHOCOLATE_CAKE("초코케이크", 15000, "디저트"),
    ICE_CREAM("아이스크림", 5000, "디저트"),
    ZERO_COLA("제로콜라", 3000, "음료"),
    RED_WINE("레드와인", 60000, "음료"),
    CHAMPAGNE("샴페인", 25000, "음료");

    private final String menu;
    private final int cost;
    private final String group;

    MenuManager(String menu, int cost, String group) {
        this.menu = menu;
        this.cost = cost;
        this.group = group;
    }

    public static MenuManager getMenuManager(String orderedMenu) {
        for (MenuManager menuManager : MenuManager.values()) {
            if (menuManager.getMenu().equals(orderedMenu)) {
                return menuManager;
            }
        }

        return null;
    }

    public String getMenu() {
        return menu;
    }

    public int getCost() {
        return cost;
    }

    public String getGroup() {
        return group;
    }
}
