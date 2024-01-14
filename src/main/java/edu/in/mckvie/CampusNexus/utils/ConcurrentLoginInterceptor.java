package edu.in.mckvie.CampusNexus.utils;
//@Component
public class ConcurrentLoginInterceptor /*implements HandlerInterceptor*/{
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("come");
//        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        request.setAttribute("cachedRequestBody", requestBody);
//        System.out.println(requestBody);
////        ObjectMapper objectMapper = new ObjectMapper();
////        JwtAuthRequest jwtAuthRequest = objectMapper.readValue(request.getInputStream(), JwtAuthRequest.class);
//
////        System.out.println("user:"+jwtAuthRequest.getUsername());
////        String username = jwtAuthRequest.getUsername();
//        String token = request.getHeader("Authorization");
//        System.out.println("token:"+token);
//        String storedToken = this.redisTemplate.opsForValue().get("username");
//        System.out.println("stored:"+storedToken);
//        if (storedToken != null /*&& !storedToken.equals(token)*/) {
//            System.out.println("if come");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User already logged in");
//            return false;
//        }
//        return true;
//    }
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // Delete the cached request body
//        request.removeAttribute("cachedRequestBody");
//    }
}
