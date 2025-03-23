package com.proyectojr.electricalsupplies.presentation.cli;

import com.proyectojr.electricalsupplies.application.ProductService;
import com.proyectojr.electricalsupplies.application.ReportService;
import com.proyectojr.electricalsupplies.application.UserService;
import com.proyectojr.electricalsupplies.application.ClientService;
import com.proyectojr.electricalsupplies.application.RoleService;
import com.proyectojr.electricalsupplies.application.ClientTypeService;
import com.proyectojr.electricalsupplies.domain.model.*;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLProductRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLReportRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLUserRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLClientRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLRoleRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLSaleDetailRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLSaleRepository;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLClientTypeRepository;
import com.proyectojr.electricalsupplies.application.SaleService;
import com.proyectojr.electricalsupplies.application.SaleDetailService;




import java.util.Scanner;

// Menú interactivo para probar las funcionalidades del sistema.

public class Main {
    public static void main(String[] args) {
        // Configurar repositorios
        var productRepository = new MySQLProductRepository();
        var userRepository = new MySQLUserRepository();
        var roleRepository = new MySQLRoleRepository();
        var clientRepository = new MySQLClientRepository();
        var clientTypeRepository = new MySQLClientTypeRepository();
        var saleRepository = new MySQLSaleRepository(); // Nuevo repositorio para ventas
        var saleDetailRepository = new MySQLSaleDetailRepository(); // Nuevo repositorio para detalles de venta
        var reportRepository = new MySQLReportRepository();

        // Configurar servicios
        var productService = new ProductService(productRepository);
        var userService = new UserService(userRepository);
        var roleService = new RoleService(roleRepository); // Corregido: Usar RoleService
        var clientService = new ClientService(clientRepository);
        var clientTypeService = new ClientTypeService(clientTypeRepository);
        var saleService = new SaleService(saleRepository); // Nuevo servicio para ventas
        var saleDetailService = new SaleDetailService(saleDetailRepository); // Nuevo servicio para detalles de venta
        var reportService = new ReportService(reportRepository);

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Usuarios y Roles");
            System.out.println("3. Gestión de Clientes y Tipos de Cliente");
            System.out.println("4. Gestión de Ventas"); // Nueva opción
            System.out.println("5. Generar Reportes"); // Nueva opción
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    menuProducts(scanner, productService);
                    break;
                case 2:
                    menuUsersAndRoles(scanner, userService, roleService);
                    break;
                case 3:
                    menuClientsAndTypes(scanner, clientService, clientTypeService);
                    break;
                case 4:
                    menuSales(scanner, saleService, saleDetailService); // Nuevo menú
                    break;
                case 5:
                    menuReports(scanner, reportService);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);

        scanner.close();
    }
    // Menu para reportes 
    private static void menuReports(Scanner scanner, ReportService reportService) {
        int option;
        do {
            System.out.println("\n=== GESTIÓN DE REPORTES ===");
            System.out.println("1. Reporte de Ventas Totales");
            System.out.println("2. Productos Más Vendidos");
            System.out.println("3. Clientes Frecuentes");
            System.out.println("0. Regresar al Menú Principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
    
            switch (option) {
                case 1:
                    System.out.print("Ingrese la fecha inicial (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Ingrese la fecha final (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    reportService.generateTotalSalesReport(startDate, endDate).forEach(report ->
                            System.out.printf("%s: %.2f (%d ventas)%n",
                                    report.getDescription(), report.getValue(), report.getCount()));
                    break;
                case 2:
                    System.out.println("Productos Más Vendidos:");
                    reportService.generateTopSellingProductsReport().forEach(report ->
                            System.out.printf("%s: %d unidades vendidas%n",
                                    report.getDescription(), (int) report.getValue()));
                    break;
                case 3:
                    System.out.println("Clientes Frecuentes:");
                    reportService.generateFrequentClientsReport().forEach(report ->
                            System.out.printf("%s: %d compras realizadas%n",
                                    report.getDescription(), (int) report.getValue()));
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    // Menú para gestionar ventas
    private static void menuSales(Scanner scanner, SaleService saleService, SaleDetailService saleDetailService) {
        int option;
        do {
            System.out.println("\n=== GESTIÓN DE VENTAS ===");
            System.out.println("1. Mostrar todas las ventas");
            System.out.println("2. Agregar una venta");
            System.out.println("3. Actualizar una venta");
            System.out.println("4. Eliminar una venta");
            System.out.println("5. Mostrar detalles de una venta");
            System.out.println("6. Agregar un detalle de venta");
            System.out.println("7. Actualizar un detalle de venta");
            System.out.println("8. Eliminar un detalle de venta");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
    
            switch (option) {
                case 1:
                    saleService.getAllSales().forEach(sale ->
                            System.out.printf("ID: %d, Cliente: %d, Usuario: %d, Total: %.2f%n",
                                    sale.getIdSale(), sale.getIdClient(), sale.getIdUser(), sale.getTotal()));
                    break;
                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    int clientId = scanner.nextInt();
                    System.out.print("Ingrese el ID del usuario: ");
                    int userId = scanner.nextInt();
                    System.out.print("Ingrese el total de la venta: ");
                    double total = scanner.nextDouble();
    
                    Sale newSale = new Sale();
                    newSale.setIdClient(clientId);
                    newSale.setIdUser(userId);
                    newSale.setTotal(total);
    
                    saleService.addSale(newSale);
                    System.out.println("Venta agregada exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID de la venta a actualizar: ");
                    int saleId = scanner.nextInt();
                    System.out.print("Ingrese el nuevo total de la venta: ");
                    double updatedTotal = scanner.nextDouble();
    
                    Sale updatedSale = new Sale();
                    updatedSale.setIdSale(saleId);
                    updatedSale.setTotal(updatedTotal);
    
                    saleService.updateSale(updatedSale);
                    System.out.println("Venta actualizada exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la venta a eliminar: ");
                    int deleteSaleId = scanner.nextInt();
                    saleService.deleteSale(deleteSaleId);
                    System.out.println("Venta eliminada exitosamente.");
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la venta: ");
                    int idSale = scanner.nextInt();
                    saleDetailService.getAllDetailsBySaleId(idSale).forEach(detail ->
                            System.out.printf("ID: %d, Producto: %d, Cantidad: %d, Precio Unitario: %.2f, Subtotal: %.2f%n",
                                    detail.getIdDetail(), detail.getIdProduct(), detail.getQuantity(),
                                    detail.getUnitPrice(), detail.getSubtotal()));
                    break;
                case 6:
                    System.out.print("Ingrese el ID de la venta: ");
                    int saleIdForDetail = scanner.nextInt();
                    System.out.print("Ingrese el ID del producto: ");
                    int productId = scanner.nextInt();
                    System.out.print("Ingrese la cantidad: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Ingrese el precio unitario: ");
                    double unitPrice = scanner.nextDouble();
    
                    SaleDetail newDetail = new SaleDetail();
                    newDetail.setIdSale(saleIdForDetail);
                    newDetail.setIdProduct(productId);
                    newDetail.setQuantity(quantity);
                    newDetail.setUnitPrice(unitPrice);
                    newDetail.setSubtotal(quantity * unitPrice);
    
                    saleDetailService.addDetail(newDetail);
                    System.out.println("Detalle de venta agregado exitosamente.");
                    break;
                case 7:
                    System.out.print("Ingrese el ID del detalle de venta a actualizar: ");
                    int detailId = scanner.nextInt();
                    System.out.print("Ingrese la nueva cantidad: ");
                    int updatedQuantity = scanner.nextInt();
                    System.out.print("Ingrese el nuevo precio unitario: ");
                    double updatedUnitPrice = scanner.nextDouble();
    
                    SaleDetail updatedDetail = new SaleDetail();
                    updatedDetail.setIdDetail(detailId);
                    updatedDetail.setQuantity(updatedQuantity);
                    updatedDetail.setUnitPrice(updatedUnitPrice);
                    updatedDetail.setSubtotal(updatedQuantity * updatedUnitPrice);
    
                    saleDetailService.updateDetail(updatedDetail);
                    System.out.println("Detalle de venta actualizado exitosamente.");
                    break;
                case 8:
                    System.out.print("Ingrese el ID del detalle de venta a eliminar: ");
                    int deleteDetailId = scanner.nextInt();
                    saleDetailService.deleteDetail(deleteDetailId);
                    System.out.println("Detalle de venta eliminado exitosamente.");
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    // Menú para gestionar productos
    private static void menuProducts(Scanner scanner, ProductService productService) {
        int option;
        do {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Mostrar todos los productos");
            System.out.println("2. Agregar un producto");
            System.out.println("3. Actualizar un producto");
            System.out.println("4. Eliminar un producto");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    productService.getAllProducts().forEach(product ->
                            System.out.printf("ID: %d, Nombre: %s, Descripción: %s, Precio: %.2f, Stock: %d%n",
                                    product.getIdProduct(), product.getName(), product.getDescription(),
                                    product.getPrice(), product.getStock()));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del producto: ");
                    String name = scanner.nextLine();
                    System.out.print("Ingrese la descripción del producto: ");
                    String description = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    double price = scanner.nextDouble();
                    System.out.print("Ingrese el stock del producto: ");
                    int stock = scanner.nextInt();
                    System.out.print("Ingrese el umbral de stock: ");
                    int threshold = scanner.nextInt();
                    Product newProduct = new Product();
                    newProduct.setName(name);
                    newProduct.setDescription(description);
                    newProduct.setPrice(price);
                    newProduct.setStock(stock);
                    newProduct.setThreshold(threshold);
                    productService.addProduct(newProduct);
                    System.out.println("Producto agregado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del producto a actualizar: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Ingrese la nueva descripción del producto: ");
                    String updatedDescription = scanner.nextLine();
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double updatedPrice = scanner.nextDouble();
                    System.out.print("Ingrese el nuevo stock del producto: ");
                    int updatedStock = scanner.nextInt();
                    System.out.print("Ingrese el nuevo umbral de stock: ");
                    int updatedThreshold = scanner.nextInt();
                    Product updatedProduct = new Product();
                    updatedProduct.setIdProduct(productId);
                    updatedProduct.setName(updatedName);
                    updatedProduct.setDescription(updatedDescription);
                    updatedProduct.setPrice(updatedPrice);
                    updatedProduct.setStock(updatedStock);
                    updatedProduct.setThreshold(updatedThreshold);
                    productService.updateProduct(updatedProduct);
                    System.out.println("Producto actualizado exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    int deleteProductId = scanner.nextInt();
                    productService.deleteProduct(deleteProductId);
                    System.out.println("Producto eliminado exitosamente.");
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);
    }
        // Menú para gestionar usuarios y roles
        private static void menuUsersAndRoles(Scanner scanner, UserService userService, RoleService roleService) {
            int option;
            do {
                System.out.println("\n=== GESTIÓN DE USUARIOS Y ROLES ===");
                System.out.println("1. Mostrar todos los usuarios");
                System.out.println("2. Agregar un usuario");
                System.out.println("3. Actualizar un usuario");
                System.out.println("4. Eliminar un usuario");
                System.out.println("5. Mostrar todos los roles");
                System.out.println("6. Agregar un rol");
                System.out.println("7. Actualizar un rol");
                System.out.println("8. Eliminar un rol");
                System.out.println("0. Regresar al menú principal");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
    
                switch (option) {
                    case 1:
                        userService.getAllUsers().forEach(user ->
                                System.out.printf("ID: %d, Nombre: %s, Email: %s%n",
                                        user.getIdUser(), user.getName(), user.getEmail()));
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre del usuario: ");
                        String userName = scanner.nextLine();
                        System.out.print("Ingrese el email del usuario: ");
                        String userEmail = scanner.nextLine();
                        System.out.print("Ingrese la contraseña del usuario: ");
                        String userPassword = scanner.nextLine();
                        User newUser = new User();
                        newUser.setName(userName);
                        newUser.setEmail(userEmail);
                        newUser.setPassword(userPassword);
                        userService.addUser(newUser);
                        System.out.println("Usuario agregado exitosamente.");
                        break;
                    case 3:
                        System.out.print("Ingrese el ID del usuario a actualizar: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Ingrese el nuevo nombre del usuario: ");
                        String updatedUserName = scanner.nextLine();
                        System.out.print("Ingrese el nuevo email del usuario: ");
                        String updatedUserEmail = scanner.nextLine();
                        System.out.print("Ingrese la nueva contraseña del usuario: ");
                        String updatedUserPassword = scanner.nextLine();
                        User updatedUser = new User();
                        updatedUser.setIdUser(userId);
                        updatedUser.setName(updatedUserName);
                        updatedUser.setEmail(updatedUserEmail);
                        updatedUser.setPassword(updatedUserPassword);
                        userService.updateUser(updatedUser);
                        System.out.println("Usuario actualizado exitosamente.");
                        break;
                    case 4:
                        System.out.print("Ingrese el ID del usuario a eliminar: ");
                        int deleteUserId = scanner.nextInt();
                        userService.deleteUser(deleteUserId);
                        System.out.println("Usuario eliminado exitosamente.");
                        break;
                    case 5:
                        roleService.getAllRoles().forEach(role ->
                                System.out.printf("ID: %d, Nombre: %s%n",
                                        role.getIdRole(), role.getName()));
                        break;
                    case 6:
                        System.out.print("Ingrese el nombre del rol: ");
                        String roleName = scanner.nextLine();
                        Role newRole = new Role();
                        newRole.setName(roleName);
                        roleService.addRole(newRole);
                        System.out.println("Rol agregado exitosamente.");
                        break;
                    case 7:
                        System.out.print("Ingrese el ID del rol a actualizar: ");
                        int roleId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Ingrese el nuevo nombre del rol: ");
                        String updatedRoleName = scanner.nextLine();
                        Role updatedRole = new Role();
                        updatedRole.setIdRole(roleId);
                        updatedRole.setName(updatedRoleName);
                        roleService.updateRole(updatedRole);
                        System.out.println("Rol actualizado exitosamente.");
                        break;
                    case 8:
                        System.out.print("Ingrese el ID del rol a eliminar: ");
                        int deleteRoleId = scanner.nextInt();
                        roleService.deleteRole(deleteRoleId);
                        System.out.println("Rol eliminado exitosamente.");
                        break;
                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (option != 0);
        }
        // Menú para gestionar clientes y tipos de cliente
    private static void menuClientsAndTypes(Scanner scanner, ClientService clientService, ClientTypeService clientTypeService) {
        int option;
        do {
            System.out.println("\n=== GESTIÓN DE CLIENTES Y TIPOS DE CLIENTE ===");
            System.out.println("1. Mostrar todos los clientes");
            System.out.println("2. Agregar un cliente");
            System.out.println("3. Actualizar un cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Mostrar todos los tipos de cliente");
            System.out.println("6. Agregar un tipo de cliente");
            System.out.println("7. Actualizar un tipo de cliente");
            System.out.println("8. Eliminar un tipo de cliente");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    clientService.getAllClients().forEach(client ->
                            System.out.printf("ID: %d, Nombre: %s, Email: %s, Teléfono: %s, Tipo: %d%n",
                                    client.getIdClient(), client.getName(), client.getEmail(),
                                    client.getPhone(), client.getClientIdType()));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del cliente: ");
                    String clientName = scanner.nextLine();
                    System.out.print("Ingrese el email del cliente: ");
                    String clientEmail = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del cliente: ");
                    String clientPhone = scanner.nextLine();
                    System.out.print("Ingrese el ID del tipo de cliente: ");
                    int clientIdType = scanner.nextInt();
                    Client newClient = new Client();
                    newClient.setName(clientName);
                    newClient.setEmail(clientEmail);
                    newClient.setPhone(clientPhone);
                    newClient.setClientIdType(clientIdType);
                    clientService.addClient(newClient);
                    System.out.println("Cliente agregado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del cliente a actualizar: ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                    String updatedClientName = scanner.nextLine();
                    System.out.print("Ingrese el nuevo email del cliente: ");
                    String updatedClientEmail = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del cliente: ");
                    String updatedClientPhone = scanner.nextLine();
                    System.out.print("Ingrese el nuevo ID del tipo de cliente: ");
                    int updatedClientIdType = scanner.nextInt();
                    Client updatedClient = new Client();
                    updatedClient.setIdClient(clientId);
                    updatedClient.setName(updatedClientName);
                    updatedClient.setEmail(updatedClientEmail);
                    updatedClient.setPhone(updatedClientPhone);
                    updatedClient.setClientIdType(updatedClientIdType);
                    clientService.updateClient(updatedClient);
                    System.out.println("Cliente actualizado exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int deleteClientId = scanner.nextInt();
                    clientService.deleteClient(deleteClientId);
                    System.out.println("Cliente eliminado exitosamente.");
                    break;
                case 5:
                    clientTypeService.getAllClientTypes().forEach(clientType ->
                            System.out.printf("ID: %d, Nombre: %s%n",
                                    clientType.getIdClientType(), clientType.getName()));
                    break;
                case 6:
                    System.out.print("Ingrese el nombre del tipo de cliente: ");
                    String clientTypeName = scanner.nextLine();
                    ClientType newClientType = new ClientType();
                    newClientType.setName(clientTypeName);
                    clientTypeService.addClientType(newClientType);
                    System.out.println("Tipo de cliente agregado exitosamente.");
                    break;
                case 7:
                    System.out.print("Ingrese el ID del tipo de cliente a actualizar: ");
                    int clientTypeId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese el nuevo nombre del tipo de cliente: ");
                    String updatedClientTypeName = scanner.nextLine();
                    ClientType updatedClientType = new ClientType();
                    updatedClientType.setIdClientType(clientTypeId);
                    updatedClientType.setName(updatedClientTypeName);
                    clientTypeService.updateClientType(updatedClientType);
                    System.out.println("Tipo de cliente actualizado exitosamente.");
                    break;
                case 8:
                    System.out.print("Ingrese el ID del tipo de cliente a eliminar: ");
                    int deleteClientTypeId = scanner.nextInt();
                    clientTypeService.deleteClientType(deleteClientTypeId);
                    System.out.println("Tipo de cliente eliminado exitosamente.");
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);
    }
}