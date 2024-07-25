package org.ustinian;

import org.ustinian.annotation.Service;

/**
 * @Description
 * @Author Ustinian12
 * @Date 2024/7/25
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
