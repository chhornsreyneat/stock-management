package com.neat;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        List<Product> product = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int currentPage = 1;
        int rowsPerPage = 2;

        product.add(new Product(1,"apple", 10,15000, LocalDate.now()) );
        product.add(new Product(2,"banana", 150,2500, LocalDate.now()) );
        product.add(new Product(3,"mango", 30,3000, LocalDate.now()) );
        product.add(new Product(4,"orange", 50,18000, LocalDate.now()) );
        product.add(new Product(5,"strawberry", 10,100000, LocalDate.now()) );

        System.out.println("\n"+
                " ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" );
        System.out.println(" STOCK MANAGEMENT SYSTEM" + "\n");

        do {
            Table table = new Table(9,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
            table.addCell(" ".repeat(2)+"| *)Display"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| W)rite"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| R)ead"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| U)pdate"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| D)elete"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| F)irst"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| P)revious"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| N)ext"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| L)ast"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| S)earch"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| G)o to"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| Se)t row"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| H)elp"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| E)xit"+" ".repeat(2));
            System.out.println(table.render());
            System.out.println();
            System.out.print("Command ———> \t");
            String options = sc.nextLine();
            switch (options) {
                case "*" -> display(product, currentPage, rowsPerPage);
                case "w", "W" -> write(product);
                case "r", "R" -> read(product);
                case "u", "U" -> update(product);
                case "d", "D" -> delete(product);
                case "f", "F" -> currentPage = first(currentPage, rowsPerPage, product);
                case "p", "P" -> currentPage = previous(currentPage, rowsPerPage, product);
                case "n", "N" -> currentPage = next(currentPage, rowsPerPage, product);
                case "l", "L" -> currentPage = last(currentPage, rowsPerPage, product);
                case "s", "S" -> search(product, currentPage, rowsPerPage);
                case "g", "G" -> currentPage = goTo(currentPage, rowsPerPage, product);
                case "se", "Se" -> rowsPerPage = setRow(rowsPerPage,product);
                case "h", "H" -> help();
                case "e", "E" -> {
                    System.out.println("good luck! ");
                    System.exit(0);
                }
                default -> System.err.println("Invalid Option");
            }
        }while (true);
    }
    public static void display(List<Product> productList, int currentPage, int rowsPerPage) {
        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, productList.size());
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);

        Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        tableDisplay.addCell(" ".repeat(2) + "ID" + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Name" + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Unit Price" + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Qty" + " ".repeat(2));
        tableDisplay.addCell(" ".repeat(2) + "Imported Date" + " ".repeat(2));

        for (int i = startIndex; i < endIndex; i++) {
            Product product = productList.get(i);
            tableDisplay.addCell(" ".repeat(2) + product.getproId().toString());
            tableDisplay.addCell(" ".repeat(2) + product.getproName());
            tableDisplay.addCell(" ".repeat(2) + product.getPrice().toString());
            tableDisplay.addCell(" ".repeat(2) + product.getQty().toString());
            tableDisplay.addCell(" ".repeat(2) + product.getImportDate().toString());
        }

        System.out.println(tableDisplay.render());
        System.out.println("~ ".repeat(60));
        System.out.println("Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total records : " + productList.size());
        System.out.println("~ ".repeat(60));
    }

    public static void write(List<Product> productList){

        Product lastProduct = productList.get(productList.size() - 1);
        Integer productID = lastProduct.getproId()+1;

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Product ID : "+productID);
            System.out.print("Product Name : ");
            String productName = sc.nextLine();
            System.out.print("Product Price : ");
            Integer productPrice = Integer.parseInt(sc.nextLine());
            System.out.print("Product Quantity : ");
            Integer productQty = Integer.parseInt(sc.nextLine());

            Product product = new Product(productID, productName, productQty, productPrice, LocalDate.now());
            productList.add(product);

            do {
                Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                table.addCell(" ID            : "+productID+" ".repeat(10));
                table.addCell(" Name          : "+productName+" ".repeat(10));
                table.addCell(" Unit price    : "+productPrice+" ".repeat(10));
                table.addCell(" Qty           : "+productQty+" ".repeat(10));
                table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                System.out.println(table.render());
                System.out.print( "Are you sure to add this record? [y/n] : ");
                String options = sc.nextLine();
                switch (options) {
                    case "y","Y" -> {
                        System.out.println("Product added successfully.");
                        return;
                    }
                    case "n","N" -> {
                        productList.remove(product);
                        System.out.println("Product is not added");
                        return;
                    }
                    default -> System.out.println("Invalid options.");
                }
            }while (true);
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
    public static void read(List<Product> productList){

        Scanner sc = new Scanner(System.in);
        boolean isFound = false;
        try {
            System.out.print("Read by ID : ");
            Integer productID = Integer.parseInt(sc.nextLine());
            for (Product product : productList) {
                if (product.getproId().equals(productID)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+productID+" ".repeat(10));
                    table.addCell(" Name          : "+product.getproName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Product with ID : "+productID+" is not found");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void update(List<Product> productList){

        Scanner sc = new Scanner(System.in);
        boolean isFound = false;
        try {
            System.out.println("Enter ID to update : ");
            Integer idToUpdate = Integer.parseInt(sc.nextLine());
            for (Product product : productList) {
                if (product.getproId().equals(idToUpdate)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+idToUpdate+" ".repeat(10));
                    table.addCell(" Name          : "+product.getproName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Product with ID : "+idToUpdate+" is not found");
            }
            Product productToUpdate = null;

            for (Product product : productList) {
                if (product.getproId().equals(idToUpdate)) {
                    productToUpdate = product;
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Product with ID: " + idToUpdate + " is not found");
                return;
            }
            Product product = productToUpdate;

            System.out.println("What do you want to update?");
            Table tableToUpdate = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND);
            tableToUpdate.addCell(" ".repeat(2)+"1. All"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"2. Name"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"3. Quantity"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"4. Price"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"5. Back to menu"+" ".repeat(2));
            System.out.println(tableToUpdate.render());
            try {
                System.out.print("Choose option (1-5) : ");
                int op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1 -> {
                        try {
                            System.out.print("Enter new product name: ");
                            String newProductName = sc.nextLine();
                            System.out.print("Enter new quantity: ");
                            Integer newQuantity = Integer.parseInt(sc.nextLine());
                            System.out.print("Enter new price: ");
                            Integer newPrice = (int) Double.parseDouble(sc.nextLine());

                            product.setproName(newProductName);
                            product.setQty(newQuantity);
                            product.setPrice(newPrice);

                            Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                            updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                            updatedTable.addCell(" Unit price    : " + newPrice + " ".repeat(10));
                            updatedTable.addCell(" Qty           : " + newQuantity + " ".repeat(10));
                            updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                            System.out.println(updatedTable.render());

                            System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y", "Y" -> {
                                    productList.add(productToUpdate);
                                    System.out.println("Product added successfully.");
                                }
                                case "n", "N" -> System.out.println("Product is not added");
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }


                    case 2 -> {
                        try {
                            System.out.println("Enter new product name : ");
                            String newProductName = sc.nextLine();
                            product.setproName(newProductName);
                            Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                            updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                            updatedTable.addCell(" Unit price    : " + product.getPrice()
                                    + " ".repeat(10));
                            updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                            updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                            System.out.println(updatedTable.render());
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y","Y" -> {
                                    productList.add(productToUpdate);
                                    System.out.println("Product added successfully.");
                                }
                                case "n","N" -> System.out.println("Product is not added");
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case 3 -> {
                        try {
                            System.out.println("Enter new product Price : ");
                            Integer newProductPrice = (int) Double.parseDouble(sc.nextLine());
                            product.setPrice(newProductPrice);
                            Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                            updatedTable.addCell(" Name          : " + product.getproName() + " ".repeat(10));
                            updatedTable.addCell(" Unit price    : " + newProductPrice + " ".repeat(10));
                            updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                            updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                            System.out.println(updatedTable.render());
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y","Y" -> {
                                    productList.add(productToUpdate);
                                    System.out.println("Product added successfully.");
                                }
                                case "n","N" -> System.out.println("Product is not added");
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case 4 -> {
                        try {
                            System.out.println("Enter new product Price : ");
                            Integer newProductQty = Integer.parseInt(sc.nextLine());
                            product.setQty(newProductQty);
                            Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                            updatedTable.addCell(" Name          : " + product.getproName() + " ".repeat(10));
                            updatedTable.addCell(" Unit price    : " + product.getPrice() + " ".repeat(10));
                            updatedTable.addCell(" Qty           : " + newProductQty + " ".repeat(10));
                            updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                            System.out.println(updatedTable.render());
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y","Y" -> {
                                    productList.add(productToUpdate);
                                    System.out.println("Product added successfully.");
                                }
                                case "n","N" -> System.out.println("Product is not added");
                                default -> System.out.println("Invalid options.");
                            }
                        } catch ( Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> System.out.println("Back to menu : ");
                    default -> throw new IllegalStateException("Unexpected value: " + op);
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
    public static void delete(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the ID of the product you want to delete: ");
            Integer productId = Integer.parseInt(scanner.nextLine());
            for (Product product : productList) {
                if (product.getproId().equals(productId)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+product.getproId()+" ".repeat(10));
                    table.addCell(" Name          : "+product.getproName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                    String options = scanner.nextLine();
                    switch (options) {
                        case "y","Y" -> {
                            productList.remove(product);
                            System.out.println("Product with ID " + productId + " deleted successfully.");
                            return;
                        }
                        case "n","N" -> {
                            System.out.println("Product is not deleted");
                            return;
                        }
                        default -> System.out.println("Invalid options");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static int first(int currentPage, int rowsPerPage, List<Product> productList) {
        if (currentPage == 1) {
            System.out.println("You are already on the first page.");
        } else {
            currentPage = 1;
            display(productList, currentPage, rowsPerPage);
        }
        return currentPage;
    }

    public static int previous(int currentPage, int rowsPerPage, List<Product> productList) {
        if (currentPage > 1) {
            currentPage--;
            display(productList, currentPage, rowsPerPage);
        } else {
            System.out.println("You are already on the first page.");
        }
        return currentPage;
    }

    public static int next(int currentPage, int rowsPerPage, List<Product> productList) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            display(productList, currentPage, rowsPerPage);
        } else {
            System.out.println("You are already on the last page.");
        }
        return currentPage;
    }

    public static int last(int currentPage, int rowsPerPage, List<Product> productList) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);
        if (currentPage == totalPages) {
            System.out.println("You are already on the last page.");
        } else {
            currentPage = totalPages;
            display(productList, currentPage, rowsPerPage);
        }
        return currentPage;
    }

    public static void search(List<Product> productList, int currentPage, int rowsPerPage) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search product by keyword: ");
        String searchKeyword = scanner.nextLine().toLowerCase();

        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : productList) {
            String productName = product.getproName().toLowerCase();

            if (productName.contains(searchKeyword)) {
                matchingProducts.add(product);
            }
        }

        int totalPages = (int) Math.ceil((double) matchingProducts.size() / rowsPerPage);
        if (matchingProducts.isEmpty()) {
            System.out.println("No products found containing the keyword '" + searchKeyword + "'.");
        } else {
            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages) {
                currentPage = totalPages;
            }

            int startIndex = (currentPage - 1) * rowsPerPage;
            int endIndex = Math.min(startIndex + rowsPerPage, matchingProducts.size());

            Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
            tableDisplay.addCell(" ".repeat(2) + "ID" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Name" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Unit Price" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Qty" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Imported Date" + " ".repeat(2));

            for (int i = startIndex; i < endIndex; i++) {
                Product product = matchingProducts.get(i);
                tableDisplay.addCell(" ".repeat(2) + product.getproId().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getproName());
                tableDisplay.addCell(" ".repeat(2) + product.getPrice().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getQty().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getImportDate().toString());
            }

            System.out.println(tableDisplay.render());
            System.out.println("~ ".repeat(60));

            System.out.println("Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total matching products: " + matchingProducts.size());
            System.out.println("~ ".repeat(60));
        }
    }
    public static int goTo(int currentPage, int rowsPerPage, List<Product> productList) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the page number you want to go to: ");

        int targetPage = scanner.nextInt();
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);

        if (targetPage >= 1 && targetPage <= totalPages) {
            currentPage = targetPage;
            display(productList, currentPage, rowsPerPage);
        } else {
            System.out.println("Invalid page number. Please enter a page number between 1 and " + totalPages + ".");
        }
        return currentPage;
    }
    public static int setRow( int rowsPerPage, List<Product> productList) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter number of row(s) you want to display : ");
            int numberOfRows = Integer.parseInt(sc.nextLine());
            if (numberOfRows >0 && numberOfRows <= productList.size()){
                return numberOfRows;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowsPerPage;
    }
    public static void help(){
        Table tableShowHelp = new Table(1,BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE,ShownBorders.SURROUND);
        tableShowHelp.addCell("1. \tPress\t * : Display all record of products");
        tableShowHelp.addCell("2. \tPress\t w : Add new product");
        tableShowHelp.addCell("   \tPress\t w ->#proName-unitPrice-qty : shortcut to add new product");
        tableShowHelp.addCell("3. \tPress\t r : Read Content any content");
        tableShowHelp.addCell("   \tPress\t r ->#proID : shortcut to read product by ID");
        tableShowHelp.addCell("4. \tPress\t u : Update data");
        tableShowHelp.addCell("5. \tPress\t d : Delete data");
        tableShowHelp.addCell("   \tPress\t d ->#proID : shortcut to delete product by ID");
        tableShowHelp.addCell("6. \tPress\t f : Display first page");
        tableShowHelp.addCell("7. \tPress\t p : Display previous page");
        tableShowHelp.addCell("8. \tPress\t n : Display next page");
        tableShowHelp.addCell("9. \tPress\t l : Display last page");
        tableShowHelp.addCell("10.\tPress\t s : Search product by name");
        tableShowHelp.addCell("11.\tPress\t h : Help");
        System.out.println(tableShowHelp.render());
    }
    public static void Exit() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("You miss to save record. Do you want to save it? [y/n] : ");

            System.exit(0);

    }

}

