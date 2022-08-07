package me.lixin.web.aspect;

/**
 * @author fanchao
 * @date 2019/8/29
 */
//@Aspect
//@Slf4j
//@Component
public class ApiLogAspect {

    /*@Autowired
    private MongoTemplate mongoTemplate;*/

   /* @Pointcut("execution(public * me.fanchaoo.web.controller.*Controller.*(..))")
    public void apiLogPointcut() {
    }*/

   /* @Around("apiLogPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        String operation = apiOperation.value();

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String url = request.getRequestURI();


        Object result = null;*/
//        ApiLogDTO apiLogDTO = ApiLogDTO.builder()
//                .uri(url)
//                .operation(operation)
//                .artistId(1L)
//                .userId(1L)
//                .userName("")
//                .build();
//
//        mongoTemplate.save(apiLogDTO);
//
//
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throw new RuntimeException(throwable);
//        } finally {
////            Query query = new Query(Criteria.where("id").is(apiLogDTO.getId()));
////            Update update = new Update().set("userName", "");
////            mongoTemplate.updateFirst(query, update, ApiLogDTO.class);
//        }

   /*     return result;
    }*/

}
