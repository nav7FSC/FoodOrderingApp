// Nav Singh Food Ordering App with 3 local resturants in Hicksville, New York
// Username is Nick and Password is Nick123
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FoodOrderingApp extends JFrame {
    // Defining username and password for login, and setting the username as admin and password as admin123
    private String username = "admin"; 
    private String password = "admin123";

    // Declaring the layout manager and panels of the GUI
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private List<String> cartItems = new ArrayList<>(); // Listing the items in the cart in an array list
    private List<Double> cartPrices = new ArrayList<>(); // Listing the items' prices in an array list

    // Constructor for the main application window
    public FoodOrderingApp() {
        // Seting basic JFrame properties
        setTitle("Food Ordering App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // Initialize layout manager and panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding different panels to the cardPanel
        cardPanel.add(createWelcomePanel(), "Welcome");
        cardPanel.add(createLoginPanel(), "Login");
        cardPanel.add(createRestaurantPanel(), "Restaurant");
        
        // Introducing the types of restaurants, 3 food menu items from each restuarant, and the prices for each of the menu items
        // Listing the restuarant Blaze Pizza, with 3 pizza menu items and their prices
        cardPanel.add(createMenuPanel("Blaze Pizza", new String[]{"Red Vine Margherita Pizza", "Double Pepperoni Pizza", "Vegetarian Pizza"},
                new double[]{12.99, 8.99, 9.99}, new String[]{"src/MargRedVine.jpg", "src/DoublePepperoni.jpg", "src/VeggiePizza.jpg"}, "https://blazepizza.com/"), "Blaze Pizza");
        // Listing the restuarant Great Wok Chinese Food, with 3 chinese food menu items and their prices
        cardPanel.add(createMenuPanel("Great Wok Chinese Food", new String[]{"Sweet and Sour Chicken", "Beef with Broccoli", "Shrimp Fried Rice"},
                new double[]{11.95, 12.55, 9.35}, new String[]{"src/SweetSourChicken.jpg", "src/Beef-and-broccoli.jpg", "src/shrimp-fried-rice-on-plate.jpg"}, "http://greatwokhicksville.com/Menu"), "Chinese Food");
        // Listing the restuarant Taste of Mumbai Indian Food, with 3 indian food menu items and their prices
        cardPanel.add(createMenuPanel("Taste of Mumbai Indian Food", new String[]{"Chicken Curry", "Vegetable Biryani", "Tandoori Paneer Tikka"},
                new double[]{14.99, 10.99, 11.95}, new String[]{"src/chicken-curry.jpg", "src/veg-biryani.jpg", "src/tandoori_paneer_tikka.jpg"}, "https://www.tasteofmumbainy.com/menu"), "Indian Food");

        cardPanel.add(createConfirmationPanel(), "Confirmation");

        // Adding the main cardPanel to the JFrame
        add(cardPanel);

        // Shows the "Welcome" panel initially
        cardLayout.show(cardPanel, "Welcome");
    }

    // Method to create the Welcome panel
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Creating a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Nav's Food Ordering App!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Creating a login button to transition to the login panel
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        panel.add(loginButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to create the Login panel so the user can login with their username and password
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Creating instructions label for login credentials, asks the user to type in their credentials
        JLabel instructionsLabel = new JLabel("Please type your login credentials:");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(instructionsLabel, gbc);

        gbc.gridwidth = 1;

        // Create labels, text fields, and login
        // Creating the label and text field for the username, and adjusting the dimensions
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(600, 60));
        // Creating the label and text field for the password, and adjusting the dimensions
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(600, 60));
        // Creating a button for the login part
        JButton loginButton = new JButton("Login");

        // ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the entered credentials are valid
                // If it is wrong, it will display Invalid Credentials popup
                // if it is correct, it will allow the user to enter to the Restaurant section
                if (usernameField.getText().equals(username) && new String(passwordField.getPassword()).equals(password)) {
                    cardLayout.show(cardPanel, "Restaurant");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.");
                }
            }
        });
        // Adgusting the postion of the labels and text fields on the page for username, password, and login
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        return panel;
    }

    // Method to create the Restaurant panel
    private JPanel createRestaurantPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Creating buttons for different restaurants and a cart button
        JButton blazePizzaButton = new JButton("Blaze Pizza: 213 Broadway Mall, Hicksville, NY 11801");
        blazePizzaButton.addActionListener(e -> cardLayout.show(cardPanel, "Blaze Pizza"));
        JButton chineseFoodButton = new JButton("Great Wok Chinese Food: 35 Woodbury Rd, Hicksville, NY 11801");
        chineseFoodButton.addActionListener(e -> cardLayout.show(cardPanel, "Chinese Food"));
        JButton indianFoodButton = new JButton("Taste of Mumbai Indian Food: 153 Levittown Pkwy, Hicksville, New York 11801");
        indianFoodButton.addActionListener(e -> cardLayout.show(cardPanel, "Indian Food"));
        JButton cartButton = new JButton("Cart");

        // ActionListener for the cart button
        cartButton.addActionListener(e -> {
            createCartPanel();
            cardPanel.add(createCartPanel(), "Cart");
            cardLayout.show(cardPanel, "Cart");
        });

        panel.add(blazePizzaButton);
        panel.add(chineseFoodButton);
        panel.add(indianFoodButton);
        panel.add(cartButton);

        return panel;
    }

    // Method to create the Menu panel for a specific restaurant. This would show the type of restaurant, the food menu items, prices, images, and website URL
    private JPanel createMenuPanel(String restaurantName, String[] items, double[] prices, String[] imagePaths, String websiteUrl) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(items.length + 2, 1)); // Adjusting the grid layout of the panel
        JLabel titleLabel = new JLabel("Menu at " + restaurantName); // Adding the name of the resturant 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Adjusts the font and size of the text
        panel.add(titleLabel);

        // Create buttons for each menu item, an image label, and a "Back" button
        for (int i = 0; i < items.length; i++) {
            JButton itemButton = new JButton(items[i] + " - $" + prices[i]);
            int finalI = i;

            if (i == 0) {
                JButton visitWebsiteButton = createVisitWebsiteButton("Visit Website", websiteUrl); // Creates visit website button
                visitWebsiteButton.addActionListener(e -> openWebsite(websiteUrl)); // Once the user clicks this, it will open website for menu page of the resturant
                panel.add(visitWebsiteButton);
            }

            // ActionListener for each menu item button
            itemButton.addActionListener(e -> {
                cartItems.add(items[finalI]);
                cartPrices.add(prices[finalI]);
                JOptionPane.showMessageDialog(null, items[finalI] + " added to cart!"); // Message will show up once user adds an item to their cart
            });
            panel.add(itemButton);

            // Resize and display the menu item image
            ImageIcon originalIcon = new ImageIcon(imagePaths[i]);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Sets the width and height of the images
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            JLabel imageLabel = new JLabel(resizedIcon);
            panel.add(imageLabel);
        }

        // Create a "Back" button so the user can access other restaurants
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Restaurant")); // Once back button is click it will go to the restaurant panel
        panel.add(backButton);
        return panel;
    }

    // Method to create a button to visit the restaurant's website
    private JButton createVisitWebsiteButton(String itemName, String websiteUrl) {
        JButton visitWebsiteButton = new JButton(itemName);
        return visitWebsiteButton;
    }

    // Method to create the Cart panel
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);

        // Display cart items and prices in a JTextArea
        StringBuilder cartDetails = new StringBuilder("Cart Items:\n");
        for (int i = 0; i < cartItems.size(); i++) {
            cartDetails.append(cartItems.get(i)).append(" - $").append(cartPrices.get(i)).append("\n");
        }
        cartTextArea.setText(cartDetails.toString());

        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        // Setting the dimensions of the cart text area
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        // Calculate and display the total amount
        double totalAmount = calculateTotalAmount();
        JLabel totalLabel = new JLabel("Total Amount: $" + totalAmount);
        // Setting the dimensions of the Total Amount text area
        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(totalLabel, gbc);

        // Create "Back" and "Place Order" buttons
        // Clicking the back button will lead to going back to the restaurant
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Restaurant"));  
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(backButton, gbc);

        // Once the user clicks the place order button, the user will be switched to the confirmation panel
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> cardLayout.show(cardPanel, "Confirmation")); 
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(placeOrderButton, gbc);

        return panel;
    }

    // Method to calculate the total amount of items in the cart
    private double calculateTotalAmount() { 
        double totalAmount = 0;
        for (Double price : cartPrices) {
            totalAmount += price;
        }
        return totalAmount;
    }

    // Method to create the Confirmation panel
    private JPanel createConfirmationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        // Instructions for the confirmation page
        JLabel instructionsLabel = new JLabel("Please type your delivery information:");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(instructionsLabel, gbc);

        gbc.gridwidth = 1;
        // Creating a label and name field for name, phone number, and delivery address
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(600, 80)); // Sets the size and dimensions of the name field

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(600, 80)); // Sets the size and dimensions of the phone number field

        JLabel addressLabel = new JLabel("Delivery Address:");
        JTextField addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(600, 80)); // Sets the size and dimensions of the delivery address field

        // Sets the position of the name label/field, phone label/field, and address lebel/field in the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(addressField, gbc);

        // Creating "Submit Order" and "Back" buttons
        JButton submitButton = new JButton("Submit Order");
        JButton backButton = new JButton("Back");

        // ActionListener for the "Submit Order" button
        submitButton.addActionListener(e -> {
            // Displaying order details in a message dialog
            StringBuilder orderDetails = new StringBuilder("Order Details:\n");

            for (int i = 0; i < cartItems.size(); i++) {
                orderDetails.append(cartItems.get(i)).append(" - $").append(cartPrices.get(i)).append("\n");
            }
            // Order submmitted message will show up once the user clicks place order
            JOptionPane.showMessageDialog(null, orderDetails.toString() + "Order submitted successfully!");

            // Clear the cart and go back to the "Welcome" panel
            cartItems.clear();
            cartPrices.clear();
            cardLayout.show(cardPanel, "Welcome");
        });

        // ActionListener for the "Back" button
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Cart"));
        
        // Setting the postion of the back button on the panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        // Setting the postion of the submit button on the panel
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(submitButton, gbc);

        return panel;
    }

    // Method to open a website in the default web browser
    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FoodOrderingApp app = new FoodOrderingApp();
            app.setVisible(true);
        });
    }
}
