//package net.javaguides.springboot.service.impl;
//
//import net.javaguides.springboot.exception.ResourceNotFoundException;
//import net.javaguides.springboot.model.User;
//import net.javaguides.springboot.model.UserWarehouse;
//import net.javaguides.springboot.repository.UserRepository;
//import net.javaguides.springboot.repository.UserWarehouseRepository;
//import net.javaguides.springboot.service.UserWarehouseService;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//public class UserWarehouseServiceImpl implements UserWarehouseService {
//    private UserWarehouseRepository userWarehouseRepository;
//
//    public UserWarehouseServiceImpl(UserWarehouseRepository userWarehouseRepository) {
//        this.userWarehouseRepository = userWarehouseRepository;
//    }
//
//    @Override
//    public UserWarehouse saveUserWarehouse(UserWarehouse userWarehouse) {
//        return userWarehouseRepository.save(userWarehouse);
//    }
//
//    @Override
//    public List<UserWarehouse> getAllUserWarehouse() {
//        return userWarehouseRepository.findAll();
//    }
//
//    @Override
//    public UserWarehouse getUserWarehouseById(int id) {
//        return userWarehouseRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("User", "Id", id));
//    }
//
//    @Override
//    public UserWarehouse updateUser(User user, int id) {
//        return null;
//    }
//
//    @Override
//    public void deleteUserWarehouse(int id) {
//        userWarehouseRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("User", "Id", id));
//        userWarehouseRepository.deleteById(id);
//    }
//
//    @Override
//    public Page<User> getAllUsersByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer userId, Integer warehouseId) {
//        return null;
//    }
//}
