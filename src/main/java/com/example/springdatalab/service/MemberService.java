package com.example.springdatalab.service;

import com.example.springdatalab.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


    public String addMember(MemberDTO memberDTO) {
        // Write the method to add the member to the data store
        return "New member has been added!";
    }

    public MemberDTO getMember(String lastName) {
        // Write method to get the member associated with the last name
        return null;
    }

    public MemberDTO updateMember(Integer id, MemberDTO memberDTO) {
        // Write method to update the member associated with id
        return null;
    }

    public String deleteMember(Integer id) {
        // Write method to delete the member associated with id
        return String.format("Member with ID %d was deleted", id);
    }
}
