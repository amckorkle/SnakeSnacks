public class Player {
    private int roundScore = 0;
    private int gameScore = 0;
	private JPanel panel;
    private String color;
    private JTextField roundWins;
    private JTextField gameWins;
    private JLabel colorLabel;
    private JLabel roundLabel;
    private JLabel gameLabel;
	private String playerOwner;

    public Player(String player){
        playerOwner = player;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        colorLabel = new JLabel("COLOR: ");
        roundLabel = new JLabel("ROUND SCORE: ") ;

    }

    public void incrementRoundWins(String playerOwner) {
	    roundWins++;
	    panel.refresh();
    }

}