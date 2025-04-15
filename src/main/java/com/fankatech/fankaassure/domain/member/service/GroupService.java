package com.fankatech.fankaassure.domain.member.service;

import com.fankatech.fankaassure.dto.member.GroupDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GroupService {
    public GroupDTO createGroup(@Valid GroupDTO groupDTO) {
        return null;
    }

    public Page<GroupDTO> getAllGroups(String name, Pageable pageable) {
        return null;
    }

    public GroupDTO getGroupById(Long id) {
        return null;
    }

    public GroupDTO updateGroup(Long id, @Valid GroupDTO groupDTO) {
        return null;
    }

    public GroupDTO addMembersToGroup(Long id, List<Long> customerIds) {
        return null;
    }

    public void removeMemberFromGroup(Long groupId, Long customerId) {
        return;
    }

    public GroupDTO importGroupMembers(Long groupId, MultipartFile file) {
        return null;
    }
}
