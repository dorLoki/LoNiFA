package de.lonifa.dnd.boundary.character.inventory;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.character.inventory.InventorySwapDTO;
import de.lonifa.dnd.service.character.inventory.InventoryService;
import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Controller
@RequestMapping("/dnd")
public class InventoryConstoller {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/swap-inventory-items")
    public ResponseEntity<?> swapInventoryItems(@RequestBody @Valid InventorySwapDTO swapDTO) {

        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (AuthenticationException | UsernameNotFoundException e) {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        }
        
        if (user == null) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).build();
        }

        if (swapDTO == null) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).build();
        }

        try {
            inventoryService.swapItems(swapDTO, user);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(Response.SC_BAD_REQUEST).build();
        }
        return ResponseEntity.status(Response.SC_OK).build();
    }

}
