public class Merchant implements Seller {
    public enum Goods { POTION }

    @Override
    public String sell(Goods goods) {
        switch (goods) {
            case POTION:
                return "potion";
        }

        return "";
    }
}
