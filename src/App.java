import javax.swing.JFrame;

public class App {
    private Player player;
    private Seller store;
    private JFrame frame;
    private PageManager manager;

    public App(Player p, Seller s) {
        player = p;
        store = s;

        player.setStoreView(store.getInventory());

        manager = new PageManager(player, store);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(manager.getJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
