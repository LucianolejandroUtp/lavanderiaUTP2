/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.extra;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;

/**
 *
 * @author desti
 */
public class MyCustomizer implements DescriptorCustomizer {
  @Override
  public void customize(ClassDescriptor descriptor) {
    descriptor.getQueryManager().setDeleteSQLString("Update DEPARTAMENTO set STATUS = 'deleted' where EMP_ID = #EMP_ID");
    // Optionally add a query key for status.
    descriptor.addDirectQueryKey("status", "STATUS");
  }
}