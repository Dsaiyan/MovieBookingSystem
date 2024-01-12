package com.scaler.practicebookmyshow.repositories;

import com.scaler.practicebookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    //SQLQuery
    //JpaRepository -> In build support for all type of queries
    // to use this functionality make userRepository as Interface and
    // extends another interface( JpaRepository<T,ID> )


    @Override
    Optional<User> findById(Long aLong);
}
