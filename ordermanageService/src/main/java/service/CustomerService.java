package service;

import dao.CustomerDao;
import domain.Customer;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.findCustomerByName(username);
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role r : customer.getRoles()){
            list.add(new SimpleGrantedAuthority(r.getRoleDesc()));
        }
        User user = new User(customer.getUsername(),
                customer.getPassword(),
                customer.getStatus()==0?false:true,
                true,
                true,
                true,
                list);
        return user;
    }

    public List<Customer> findAllC(){
        return customerDao.findAllC();
    }

    public void addCustomer(Customer customer){
        customer.setId(UUID.randomUUID().toString());
        customer.setPassword(encoder.encode(customer.getPassword()));
        customerDao.addCustomer(customer);
    }

    public Customer findCustomerById(String id){
        return customerDao.findCustomerById(id);
    }

    public void addRoleToCustomer(String cid,String[] rids){
        for (String rid:rids){
            customerDao.addRoleToCustomer(cid,rid);
        }
    }
}
